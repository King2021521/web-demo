package com.nd.me.component.nio.netty.support;

/**
 * @Author
 * @Description
 * @Date Create in 下午 1:57 2018/9/18 0018
 */
public class Header {
    private int magic;

    private byte version;

    private byte type;

    private int sequence;

    private int length;

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
