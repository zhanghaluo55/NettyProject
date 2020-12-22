package com.hongpro.netty.protocoltcp;

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
public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    private int count;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageProtocol messageProtocol) throws Exception {
        //接收数据并处理
        int len = messageProtocol.getLen();
        byte[] content = messageProtocol.getContent();
        System.out.println("服务器接收到信息如下:");
        System.out.println("长度 = " + len);
        System.out.println("内容 = " + new String(content, StandardCharsets.UTF_8));
        System.out.println("服务器接收到的消息总包数: " + (++this.count));

        //回复消息
        String responseContent = UUID.randomUUID().toString();
        int responseLen = responseContent.getBytes(StandardCharsets.UTF_8).length;
        byte[] bytes = responseContent.getBytes(StandardCharsets.UTF_8);

        MessageProtocol messageProtocol1 = new MessageProtocol();
        messageProtocol1.setLen(responseLen);
        messageProtocol1.setContent(bytes);
        channelHandlerContext.writeAndFlush(messageProtocol1);

    }
}
