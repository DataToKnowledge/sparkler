package it.dtk.streaming.receivers

import akka.actor.Actor
import it.dtk.kafka.{ConsumerProperties}
import it.dtk.model.Article
import org.apache.kafka.common.TopicPartition
import org.apache.spark.streaming.receiver.ActorHelper
import org.json4s.NoTypeHints
import org.json4s.ext.JodaTimeSerializers
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization

import scala.collection.JavaConversions._
import scala.concurrent.duration._

/**
  * Created by fabiofumarola on 28/02/16.
  */
class KafkaArticlesActor(props: ConsumerProperties, beginning: Boolean = false) extends Actor with ActorHelper {
  implicit val formats = Serialization.formats(NoTypeHints) ++ JodaTimeSerializers.all

  import context.dispatcher

//  val consumer = new KafkaReader(props)

//  if (beginning) {
//    consumer.poll()
//    consumer.consumer.seekToBeginning(new TopicPartition(props.topics, 0))
//  }

  context.system.scheduler.scheduleOnce(50 millis, self, "start")

  override def receive: Receive = {

    case "start" =>
//      consumer.poll().foreach { rec =>
//        val url = new String(rec.key())
//        val article = parse(new String(rec.value())).extract[Article]
//        store(url -> article)
//      }
//      self ! "start"
  }

  override def postStop(): Unit = {
//    consumer.close()
  }
}
