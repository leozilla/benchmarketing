package com.leonhart.ws.bench.sut.echo.jetty.javax;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ClientEndpoint
@ServerEndpoint(value="/")
public class SyncPingSocket
{
    private Session session;

    @OnOpen
    public void onWebSocketConnect(final Session sess)
    {
        this.session = sess;
        System.out.println("Socket Connected: " + sess);
    }

    @OnMessage
    public void onWebSocketText(String message) throws IOException {
        session.getBasicRemote().sendText("pong");
        System.out.println("Received TEXT message: " + message);
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason)
    {
        System.out.println("Socket Closed: " + reason);
    }

    @OnError
    public void onWebSocketError(Throwable cause)
    {
        cause.printStackTrace(System.err);
    }
}