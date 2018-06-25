package com.piggymetrics.account

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

object AccountService {
  val DEFAULT_HEADER = Map("Content-Type" -> "application/json")
  //curl -v 'http://localhost:4000/accounts/'  -H 'Content-Type: application/json'  --data '{"username":"gatling2f2d04f2-0","password":"test1234567"}'
  val createUser = {
    exec(
      http("account-service[register]")
        .post("/accounts/")
        .headers(DEFAULT_HEADER)
        .body(
          StringBody("""{"username":"${userName}","password":"password"}"""))
        .check(status.is(200))
    ).pause(10 milliseconds)
  }

  //curl -v 'http://localhost:4000/accounts/current' -H 'Authorization: Bearer 49ccffa2-517c-44cc-9a25-5339c112ea15'
  val getUser = {
    exec(
      http("account-service[current]")
        .get("/accounts/current")
        .headers(DEFAULT_HEADER)
        .header("Authorization", "Bearer ${access_token}")
        .check(status.is(200))
    ).pause(10 milliseconds)
  }
}
