package com.github.zereao.netty.demo02;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @author Zereao
 * @version 2018/06/13/16:37
 */
public class ServerOutboundHandler_2 extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        Number n = (Number) msg;
        System.out.println("ServerOutboundHandler_2 ------- get num = " + n.getNum());
        n.add();
        ctx.writeAndFlush(n);
//        ctx.channel().writeAndFlush(n);
    }
}
