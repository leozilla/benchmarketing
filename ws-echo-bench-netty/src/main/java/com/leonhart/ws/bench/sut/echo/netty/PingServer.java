package com.leonhart.ws.bench.sut.echo.netty;

public class PingServer {
    public static void main(String[] args) throws InterruptedException {
        WebSocketServer server = new WebSocketServer();
        server.bind(8080);

        Thread.sleep(Integer.MAX_VALUE);
    }
}
