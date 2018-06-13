package com.github.zereao.netty.demo02;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Zereao
 * @version 2018/06/13/16:35
 */
public class ServerInboundHandler_2 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        Number n = (Number) msg;
        System.out.println("ServerInboundHandler_2  ---- after fire ----- get num = " + n.getNum());
        n.add();
        ctx.writeAndFlush(n);
//        ctx.channel().writeAndFlush(n);
    }
}
