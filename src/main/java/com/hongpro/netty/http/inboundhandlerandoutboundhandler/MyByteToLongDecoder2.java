package com.hongpro.netty.http.inboundhandlerandoutboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * TODO
 *
 * @author zhangzihong
 * @data 2020/12/21 12:25
 */
public class MyByteToLongDecoder2 extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("MyByteToLongDecoder2 被调用");
        //不需要判断数据是否足够读取，内部会进行处理判断
        list.add(byteBuf.readLong());
    }
}
