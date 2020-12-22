package com.hongpro.netty.protocoltcp;

/**
 * TODO 协议包
 *
 * @author zhangzihong
 * @data 2020/12/22 15:38
 */
public class MessageProtocol {
    private int len;  //关键
    private byte[] content;

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
