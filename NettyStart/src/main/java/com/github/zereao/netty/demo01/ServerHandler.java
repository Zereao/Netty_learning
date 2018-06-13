package com.github.zereao.netty.demo01;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Zereao
 * @version 2018/06/13/14:14
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("ServerHandler channelRead .....");
        ByteBuf buf = (ByteBuf) msg;
        byte[] data = new byte[buf.readableBytes()];
        buf.readBytes(data);
        String result = new String(data, "utf-8");
        System.out.println("这里是服务器，接收到客户端说：" + result);

        // 写给客户端
        String response = "我是来自服务端ServerHandler的反馈信息";
        ctx.writeAndFlush(response.getBytes());
    }
}
