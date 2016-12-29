package com.leonhart.ws.echo.loadgen.gatling

import scala.language.postfixOps
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class WsEchoSimulation extends AbstractWsSimulation {

  val echo = scenario("echo")
    .exec(ws("Connect WS").open("/"))
    .pause(1 second)
    .repeat(5000, "i") {
      exec(ws("Ping/Pong")
          .sendText("ping")
          .check(wsListen.within(10 milliseconds).until(1).regex("pong")))
    }
    .pause(1 second)
    .exec(ws("Close WS").close)

  setUp(echo.inject(atOnceUsers(1))).protocols(wsConf)
}
