package com.leonhart.ws.echo.loadgen.gatling

import io.gatling.core.Predef._

import scala.language.postfixOps
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import scala.concurrent.duration._

class WsEchoSimulation extends Simulation {

  val counterName: String = "counter"

  val wsConf = http
    .baseURL("http://localhost:8080")
    .acceptLanguageHeader("de-DE,de;q=0.8,en-US;q=0.6,en;q=0.4")
    .acceptEncodingHeader("gzip, deflate, sdch")
    .userAgentHeader("Gatling2")
    .wsBaseURL("ws://localhost:8080")

  val echo = scenario("echo")
    .exec(ws("Connect WS").open("/"))
    .repeat(10, "i") {
      exec(ws("Ping/Pong")
        .sendText("ping")
        .check(wsAwait.within(1 seconds).until(1).regex("pong")))
    }
    .exec(ws("Close WS").close)

  setUp(echo.inject(atOnceUsers(1))).protocols(wsConf)
}
