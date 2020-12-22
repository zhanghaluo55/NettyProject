package com.hongpro.netty.tcp;

import com.hongpro.netty.http.inboundhandlerandoutboundhandler.MyClientHandler;
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
        pipeline.addLast(new MyClientHandler());
    }
}
