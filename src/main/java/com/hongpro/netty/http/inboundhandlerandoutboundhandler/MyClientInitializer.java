package com.hongpro.netty.http.inboundhandlerandoutboundhandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * TODO
 *
 * @author zhangzihong
 * @data 2020/12/10 12:20
 */
public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        ChannelPipeline pipeline = socketChannel.pipeline();

        //入站的handler进行编码
        pipeline.addLast(new MyLongToByteEncoder());
        //出战
        pipeline.addLast(new MyByteToLongDecoder());
        //业务逻辑
        pipeline.addLast(new MyClientHandler());
    }
}
