package com.hongpro.netty.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;

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
public class MyClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private int count;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务器的ip:" + ctx.channel().remoteAddress());
        for (int i = 0; i < 10; i++) {
            ByteBuf buf = Unpooled.copiedBuffer("hello,server " + i, StandardCharsets.UTF_8);
            ctx.writeAndFlush(buf);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        byte[] buffer = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(buffer);

        String message = new String(buffer, StandardCharsets.UTF_8);
        System.out.println("客户端接收到的消息:" + message);
        System.out.println("客户端接收的消息数量:" + (++this.count));
    }
}
