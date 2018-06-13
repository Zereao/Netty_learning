package com.github.zereao.netty.demo02;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Zereao
 * @version 2018/06/13/16:14
 */
public class ClientHandler_1 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Number n = (Number) msg;
        System.out.println("接收到的数字为 ： " + n.getNum());
        n.add();
        ctx.writeAndFlush(n);
//        ctx.channel().writeAndFlush(n);
    }
}
