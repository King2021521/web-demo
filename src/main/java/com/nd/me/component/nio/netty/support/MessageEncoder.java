package com.nd.me.component.nio.netty.support;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author
 * @Description
 * @Date Create in 下午 1:53 2018/9/18 0018
 */
public class MessageEncoder extends MessageToByteEncoder<MessagePayload<String>> {

    private static final String DEFAULT_ENCODE = "utf-8";

    private static final int MAGIC_NUMBER = 0x0CAFFEE0;

    public MessageEncoder() {
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, MessagePayload<String> msg, ByteBuf out) throws Exception {

        @SuppressWarnings("resource")
        ByteBufOutputStream writer = new ByteBufOutputStream(out);
        byte[] body = null;

        if (null != msg && null != msg.getBody() && "" != msg.getBody()) {
            body = msg.getBody().getBytes(DEFAULT_ENCODE);
        }

        writer.writeInt(MAGIC_NUMBER);

        writer.writeByte(1);

        writer.writeByte(msg.getHeader().getType());

        writer.writeInt(msg.getHeader().getSequence());

        if (null == body || 0 == body.length) {
            writer.writeInt(0);
        } else {
            writer.writeInt(body.length);
            writer.write(body);
        }
    }

}
