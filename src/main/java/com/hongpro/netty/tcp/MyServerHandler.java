package com.hongpro.netty.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

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
public class MyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private int count;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        byte[] buffer = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(buffer);

        String message = new String(buffer, StandardCharsets.UTF_8);
        System.out.println("服务器接收的数据:" + message);
        System.out.println("服务器端接收的消息量: " + (++this.count));

        //回送数据给客户端
        ByteBuf buf = Unpooled.copiedBuffer(UUID.randomUUID().toString(), StandardCharsets.UTF_8);
        channelHandlerContext.writeAndFlush(buf);
    }
}
