package com.leonhart.ws.bench.sut.echo.jetty.vanilla;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;

import java.io.IOException;

public class PingSocket extends WebSocketAdapter {

    private Session session;

    @Override
    public void onWebSocketConnect(Session sess)
    {
        super.onWebSocketConnect(sess);
        this.session = sess;
        System.out.println("Socket Connected: " + sess);
    }

    @Override
    public void onWebSocketText(String message)
    {
        super.onWebSocketText(message);
        try {
            session.getRemote().sendString("pong");
        } catch (IOException e) {
        }

        // System.out.println("Received TEXT message: " + message);
    }

    @Override
    public void onWebSocketClose(int statusCode, String reason)
    {
        super.onWebSocketClose(statusCode,reason);
        System.out.println("Socket Closed: [" + statusCode + "] " + reason);
    }

    @Override
    public void onWebSocketError(Throwable cause)
    {
        super.onWebSocketError(cause);
        cause.printStackTrace(System.err);
    }
}
