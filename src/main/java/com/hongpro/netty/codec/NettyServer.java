package com.hongpro.netty.codec;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

/**
 * TODO
 *
 * @author zhangzihong
 * @data 2020/12/5 15:16
 */
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        //两个线程组 boss只处理连接请求 work做业务处理
        //两个都无限循环
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //创建启动对象，配置参数
            ServerBootstrap bootstrap = new ServerBootstrap();

            //链式编程设置参数
            bootstrap.group(bossGroup, workerGroup)  //设置线程组
                    .channel(NioServerSocketChannel.class)  //作为服务器通道实现
                    .option(ChannelOption.SO_BACKLOG, 128)  //线程队列连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true) //设置保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() {  //给work 的 EventLoopGroup设置处理器
                        //创建一个通道初始化对象 匿名创建 给pipeline设置处理器
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast("decoder", new ProtobufDecoder(StudentPOJO.Student.getDefaultInstance()));
                            pipeline.addLast(new NettyServerHandler());
                        }
                    });
            System.out.println("...服务器 is ready");
            //绑定一个端口并且同步，生成一个ChannelFuture对象 启动服务器端口
            ChannelFuture cf = bootstrap.bind(6668).sync();

            //注册监听器，监控我们关心的事件
            cf.addListener((ChannelFutureListener) channelFuture -> {
                if (cf.isSuccess()) {
                    System.out.println("监听端口 6668 成功");
                } else {
                    System.out.println("监听端口 6668 失败");
                }
            });

            //对关闭通道进行监听
            cf.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
