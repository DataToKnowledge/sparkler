package it.dtk.jobs.init

import it.dtk.model._
import org.joda.time.DateTime

/**
  * Created by fabiofumarola on 30/01/16.
  */
object FakeDB {

  def listFeeds(): List[Feed] = {
    List(
      Feed(
        publisher = "new.google.com",
        url = "https://news.google.com/?output=rss&q=furti&rsz=8",
        parsedUrls = List.empty[String],
        lastTime = Some(DateTime.now())
      ),
      Feed(
        publisher = "new.google.com",
        url = "https://news.google.com/?output=rss&q=rapine&rsz=8",
        parsedUrls = List.empty[String],
        lastTime = Some(DateTime.now())
      ),
      Feed(
        publisher = "new.google.com",
        url = "https://news.google.com/?output=rss&q=furti%20bari&rsz=8",
        parsedUrls = List.empty[String],
        lastTime = Some(DateTime.now())
      ),
      Feed(
        publisher = "new.google.com",
        url = "https://news.google.com/?output=rss&q=violenza&rsz=8",
        parsedUrls = List.empty[String],
        lastTime = Some(DateTime.now())
      )
    )
  }
}
