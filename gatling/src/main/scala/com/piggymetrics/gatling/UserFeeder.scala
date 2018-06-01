package com.piggymetrics.gatling


import faker._
import io.gatling.core.feeder.Feeder
object UserFeeder {
  val r = new scala.util.Random(20)
  val feeder = new Feeder[String] {
    override def hasNext = true

    override def next: Map[String, String] = {
      Map("userName" -> (Name.first_name + "-" + Name.last_name))
    }
  }
}