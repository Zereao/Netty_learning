package com.github.zereao.netty.demo02;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @author Zereao
 * @version 2018/06/13/16:36
 */
public class ServerOutboundHandler_1 extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        Number n = (Number) msg;
        System.out.println("ServerOutboundHandler_1 ------- get num = " + n.getNum());
        n.add();
        ctx.writeAndFlush(n);
//        ctx.channel().writeAndFlush(n);
    }
}
