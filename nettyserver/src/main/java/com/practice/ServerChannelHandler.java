package com.practice;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @Author xuetao
 * @Description: server handle
 * @Date 2019-09-23
 * @Version 1.0
 */
public class ServerChannelHandler implements ChannelInboundHandler {
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("Server received: " + byteBuf.toString(CharsetUtil.UTF_8));
        String o = "Hello Client";
        byteBuf.clear();
        ctx.write(byteBuf.writeBytes(o.getBytes()));
    }

    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    public void channelUnregistered(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }

    public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

}
