package com.practice;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.charset.Charset;

/**
 * @Author xuetao
 * @Description: netty服务端
 * @Date 2019-09-22
 * @Version 1.0
 */
public class NettyServer {

    private final int port;

    public NettyServer(int port) {
        this.port = port;
    }


    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(group).channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline()
                                    .addLast(new ServerChannelHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.bind().sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {

        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        new NettyServer(8080).start();
    }
}
