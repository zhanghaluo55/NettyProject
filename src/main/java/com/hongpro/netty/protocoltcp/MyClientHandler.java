package com.hongpro.netty.protocoltcp;

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
public class MyClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {
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
            String mes = "今天天气冷，吃火锅";
            byte[] content = mes.getBytes(StandardCharsets.UTF_8);
            int length = mes.getBytes(StandardCharsets.UTF_8).length;

            MessageProtocol messageProtocol = new MessageProtocol();
            messageProtocol.setLen(length);
            messageProtocol.setContent(content);
            ctx.writeAndFlush(messageProtocol);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageProtocol messageProtocol) throws Exception {
        int len = messageProtocol.getLen();
        byte[] content = messageProtocol.getContent();

        System.out.println("客户端接收消息如下:");
        System.out.println("长度 = " + len);
        System.out.println("内容 = " + new String(content, StandardCharsets.UTF_8));

        System.out.println("客户端接收消息总数:" + (++this.count));
    }
}
