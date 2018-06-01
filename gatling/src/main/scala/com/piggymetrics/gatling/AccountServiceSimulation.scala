package com.piggymetrics.gatling

import java.util.concurrent.TimeUnit

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration.Duration

class AccountServiceSimulation extends Simulation {

  val httpConf = http
    .baseURL("http://gateway:4000") // Here is the root for all relative URLs
    .acceptHeader("application/json,text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")


  //register user
  //curl -v 'http://localhost:4000/accounts/'  -H 'Content-Type: application/json'  --data '{"username":"gatling2f2d04f2-0","password":"test1234567"}'

  //token
  //curl -v -X POST 'http://localhost:4000/uaa/oauth/token?scope=ui&username=test123&password=test123&grant_type=password'  -H 'Authorization: Basic YnJvd3Nlcjo='
  //current
  //curl -v 'http://localhost:4000/accounts/current' -H 'Authorization: Bearer 49ccffa2-517c-44cc-9a25-5339c112ea15'

  val scn = scenario("register").repeat(1, "n") {
    feed(UserFeeder.feeder).exec(
      http("account-service[register]")
        .post("/accounts/")
        .header("Content-Type", "application/json")
        .body(StringBody("""{"username":"${userName}","password":"password"}"""))
        .check(status.is(200))
    ).pause(Duration.apply(5, TimeUnit.MILLISECONDS))
  }.doIf("${userName.exists()}") {
    repeat(1, "n") {
      exec(
        http("auth-service[token]")
          .post("/uaa/oauth/token?scope=ui&username=${userName}&password=password&grant_type=password")
          .header("Authorization", "Basic YnJvd3Nlcjo=")
          .check(status.is(200), jsonPath("$.access_token").find.saveAs("access_token"))
      )
    }
  }.doIf("${access_token.exists()}") {
    repeat(1, "n") {
      exec(
        http("account-service[current]")
          .get("/accounts/current")
          .header("Content-Type", "application/json")
          .header("Authorization", "Bearer ${access_token}")
          .check(status.is(200))
      )
    }
  }

  setUp(scn.inject(atOnceUsers(10)).protocols(httpConf))
}
