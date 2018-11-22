package com.nd.me.component.nio.netty;

import com.nd.me.component.nio.netty.support.MessagePayload;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @Author
 * @Description
 * @Date Create in 下午 2:37 2018/6/15 0015
 */
public class NettyServerReadHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        /*ByteBuf buffer = (ByteBuf) msg;
        byte[] req = new byte[buffer.readableBytes()];
        buffer.readBytes(req);
        String body = new String(req, "UTF-8");*/
        String body = msg;
        System.out.println("客户端传来的数据：" + body);
        ByteBuf resp = Unpooled.copiedBuffer(new Date().toString().getBytes());
        ctx.write(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
