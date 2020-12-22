package com.hongpro.netty.http.inboundhandlerandoutboundhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * TODO
 *
 * @author zhangzihong
 * @data 2020/12/10 12:25
 */
/*
    1. SimpleChannelInboundHandler是ChannelInboundHandlerAdapter
    2. HttpObject 客户端和服务端相互通信的数据
 */
public class MyClientHandler extends SimpleChannelInboundHandler<Long> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Long aLong) throws Exception {
        System.out.println("服务器的ip:" + channelHandlerContext.channel().remoteAddress());
        System.out.println("消息:" + aLong);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyClientHandler 发送数据");
        ctx.writeAndFlush(12345L);
        //ctx.writeAndFlush(Unpooled.copiedBuffer(""));
    }
}
