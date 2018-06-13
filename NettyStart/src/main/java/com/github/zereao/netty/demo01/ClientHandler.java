package com.github.zereao.netty.demo01;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @author Zereao
 * @version 2018/06/13/14:32
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            System.out.println("ClientHandler channelRead()。。。。");
            ByteBuf buf = (ByteBuf) msg;
            byte[] data = new byte[buf.readableBytes()];
            buf.readBytes(data);
            System.out.println(new String(data, "utf8"));
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}
