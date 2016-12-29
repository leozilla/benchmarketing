package com.leonhart.ws.echo.loadgen.gatling

import io.gatling.core.Predef._

import scala.language.postfixOps
import io.gatling.http.Predef._

abstract class AbstractWsSimulation extends Simulation {

  val wsConf = http
    .baseURL("http://localhost:8080")
    .acceptLanguageHeader("de-DE,de;q=0.8,en-US;q=0.6,en;q=0.4")
    .acceptEncodingHeader("gzip, deflate, sdch")
    .userAgentHeader("Gatling2")
    .wsBaseURL("ws://localhost:8080")
}
