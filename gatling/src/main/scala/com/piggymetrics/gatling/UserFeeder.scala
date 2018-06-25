package com.piggymetrics.gatling


import faker._
import io.gatling.core.feeder.Feeder
object UserFeeder {
  val feeder = new Feeder[String] {
    override def hasNext = true

    override def next: Map[String, String] = {
      Map("userName" -> (Internet.user_name))
    }
  }
}