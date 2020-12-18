package com.hongpro.netty.codec1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

/**
 * TODO
 *
 * @author zhangzihong
 * @data 2020/12/5 15:46
 */
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        //客户端需要一个事件循环组
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {

            //客户端启动对象
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)  // 设置线程组
                    .channel(NioSocketChannel.class)  //设置客户端通道的实现类
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //加入ProtoBufEncoder
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast("encoder", new ProtobufEncoder());
                            pipeline.addLast(new NettyClintHandler());
                        }
                    });

            System.out.println("客户端 is ok ...");

            //启动客户端去连接服务器
            //关于channelFuture 要分析，涉及netty异步模型
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6668).sync();

            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
