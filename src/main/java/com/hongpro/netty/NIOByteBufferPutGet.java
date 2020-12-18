package com.hongpro.netty;

import java.io.IOException;

/**
 * TODO
 *
 * @author zhangzihong
 * @data 2020/11/25 10:16
 */
public class NIOByteBufferPutGet {
    public static void main(String[] args) throws IOException {
        /*ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);

        serverSocketChannel.socket().bind(inetSocketAddress);

        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLength = 8;

        while (true) {
            int byteRead = 0;
            while (byteRead < messageLength) {
                long l = socketChannel.read(byteBuffers);
                byteRead += 1;
                System.out.println("byteRead=" + byteRead);
                Arrays.asList(byteBuffers).stream().map
            }
        }*/
    }

}
