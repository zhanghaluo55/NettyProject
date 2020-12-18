package com.hongpro.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * TODO
 *
 * @author zhangzihong
 * @data 2020/12/11 10:46
 */
public class NettyByteBuf01 {
    public static void main(String[] args) {
        ByteBuf buffer = Unpooled.buffer(10);

        //netty的buffer中 不需要使用flip进行反转
        for (int i = 0; i < 10; i++) {
            buffer.writeByte(i);
        }

        for (int j = 0; j<buffer.capacity(); j++) {
            System.out.println(buffer.getByte(j));
        }

        //buffer.readByte()
    }
}
