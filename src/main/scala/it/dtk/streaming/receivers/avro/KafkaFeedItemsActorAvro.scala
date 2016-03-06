package it.dtk.streaming.receivers.avro

import java.io.ByteArrayInputStream

import akka.actor.Actor
import com.gensler.scalavro.types.AvroType
import it.dtk.kafka.{ConsumerProperties, KafkaReader}
import it.dtk.model.Article
import org.apache.kafka.common.TopicPartition
import org.apache.spark.streaming.receiver.ActorHelper
import org.json4s.NoTypeHints
import org.json4s.ext.JodaTimeSerializers
import org.json4s.jackson.Serialization

import scala.collection.JavaConversions._
import scala.concurrent.duration._
import scala.util.{Failure, Success}

/**
  * Created by fabiofumarola on 28/02/16.
  */
class KafkaFeedItemsActorAvro(props: ConsumerProperties, beginning: Boolean = false) extends Actor with ActorHelper {

  import context.dispatcher

  val articleAvroType = AvroType[Article]

  val consumer = new KafkaReader[Array[Byte], Array[Byte]](props)

  if (beginning) {
    consumer.poll()
    consumer.consumer.seekToBeginning(new TopicPartition(props.topics, 0))
  }

  context.system.scheduler.schedule(0 millis, 100 millis, self, "start")

  override def receive: Receive = {

    case "start" =>
      val s: ActorHelper = self.asInstanceOf[ActorHelper]

      consumer.poll().foreach { rec =>
        val url = new String(rec.key())
        articleAvroType.io.read(new ByteArrayInputStream(rec.value)) match {
          case Success(article) =>
            log.info("got article from actor")
            s.store(url -> article)

          case Failure(ex) => ex.printStackTrace()
        }
      }
//      self ! "start"
  }

  override def postStop(): Unit = {
    consumer.close()
  }
}
