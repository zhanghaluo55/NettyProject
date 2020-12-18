package com.hongpro.netty.codec;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * TODO
 *
 * @author zhangzihong
 * @data 2020/12/5 15:35
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<StudentPOJO.Student> {
    //读取客户端发送的消息 ctx上下文对象  含有pipeline,channel，地址
    // msg客户的那发送的对象
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, StudentPOJO.Student student) throws Exception {
        System.out.println("客户端发送的数据 id = " + student.getId() + "名字 = " + student.getName());
    }

    //读取消息完毕
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //write + flush 将数据写入缓存并刷新
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
