package com.piggymetrics.gatling

import com.piggymetrics.account.AccountService
import com.piggymetrics.auth.AuthService
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class AccountServiceSimulation extends Simulation {

  val baseUrl: String = sys.env.getOrElse("BASE_URL", "http://gateway:4000")

  val concurrency = sys.env.getOrElse("CONCURRENCY", "10)").toInt

  val httpConf = http
    .baseURL(baseUrl) // Here is the root for all relative URLs
    .acceptHeader(
    "application/json,text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader(
      "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")


  val users = scenario("register")
    .feed(UserFeeder.feeder)
    .exec(AccountService.createUser)
    .doIf("${userName.exists()}") {
      exec(AuthService.getAccessToken)
    }
    .doIf("${access_token.exists()}") {
      exec(AccountService.getUser)
    }

  setUp(users.inject(atOnceUsers(concurrency)).protocols(httpConf))
}
