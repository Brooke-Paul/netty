package com.practice;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @Author xuetao
 * @Description: netty 客户端
 * @Date 2019-09-22
 * @Version 1.0
 */
public class NettyClient {


    /**
     * ip 地址
     */
    private final String host;

    /**
     * 服务端口
     */
    private final int port;

    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(group).channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline()
                                    .addLast(new ClientChannelHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect().sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {

        } finally {
            group.shutdownGracefully().sync();
        }
    }


    public static void main(String[] args) throws Exception {
        new NettyClient("127.0.0.1", 8080).start();
    }
}
