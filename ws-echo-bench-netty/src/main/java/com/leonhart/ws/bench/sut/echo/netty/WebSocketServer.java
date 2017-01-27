package com.leonhart.ws.bench.sut.echo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

import java.net.InetSocketAddress;

public class WebSocketServer {
    private NioEventLoopGroup parent;
    private NioEventLoopGroup child;

    public void bind(final int port) throws InterruptedException {
        final ServerBootstrap sb = new ServerBootstrap();
        try {
            parent = new NioEventLoopGroup(1);
            child = new NioEventLoopGroup();
            sb.group(parent, child)
              .channel(NioServerSocketChannel.class)
              .localAddress(new InetSocketAddress(port))
              .childHandler(new ChannelInitializer<SocketChannel>() {
                  @Override
                  public void initChannel(final SocketChannel ch) throws Exception {
                      ch.pipeline().addLast(
                              new HttpServerCodec(),
                              new HttpObjectAggregator(65536),
                              new WebSocketServerProtocolHandler("/", null, true),
                              new TextWebSocketFrameHandler());
                  }
              });

            sb.bind().sync();
            System.out.println("Web socket server started at port " + port);

            // wsChannel.closeFuture().sync();
        } finally {
            // child.shutdownGracefully();
            // parent.shutdownGracefully();
        }
    }

    public void shutdown() {
        child.shutdownGracefully().syncUninterruptibly();
        parent.shutdownGracefully().syncUninterruptibly();
    }

    private class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            super.userEventTriggered(ctx, evt);
        }

        protected void channelRead0(final ChannelHandlerContext ctx, final TextWebSocketFrame msg) throws Exception {
            ctx.writeAndFlush(new TextWebSocketFrame("pong"));
        }
    }
}
