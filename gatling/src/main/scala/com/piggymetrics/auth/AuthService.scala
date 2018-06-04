package com.piggymetrics.auth

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

object AuthService {
  //curl -v -X POST 'http://localhost:4000/uaa/oauth/token?scope=ui&username=test123&password=test123&grant_type=password'  -H 'Authorization: Basic YnJvd3Nlcjo='
  val getAccessToken = {
    exec(
      http("auth-service[token]")
        .post("/uaa/oauth/token?scope=ui&username=${userName}&password=password&grant_type=password")
        .header("Authorization", "Basic YnJvd3Nlcjo=")
        .check(status.is(200),
               jsonPath("$.access_token").find.saveAs("access_token"))
    ).pause(10 microseconds)
  }
}
