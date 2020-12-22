package com.hongpro.netty.http.inboundhandlerandoutboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * TODO
 *
 * @author zhangzihong
 * @data 2020/12/21 12:25
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder {
    /**
     *
     * @param channelHandlerContext 上下文对象
     * @param byteBuf 入站的 ByteBuf
     * @param list list集合，将解码后的数据传给下一个handler
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List list) throws Exception {
        if (byteBuf.readableBytes() >= 8) {
            list.add(byteBuf.readLong());
        }
    }
}
