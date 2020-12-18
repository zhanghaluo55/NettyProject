package com.hongpro.netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * TODO
 *
 * @author zhangzihong
 * @data 2020/12/16 19:49
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     *
     * @param ctx 上下文
     * @param evt 事件
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            //evt向下转型
            IdleStateEvent event = (IdleStateEvent) evt;
            String evenType = null;
            switch (event.state()) {
                case READER_IDLE:
                    evenType = "读空闲";
                    break;
                case WRITER_IDLE:
                    evenType = "写空闲";
                    break;
                case ALL_IDLE:
                    evenType = "读写空闲";
                    break;
            }
            System.out.println(ctx.channel().remoteAddress() + "--超过时间--" + evenType);

            //发生空闲，关闭通道
            ctx.channel().close();
        }
    }
}
