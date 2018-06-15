package com.github.zereao.netty.demo02;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * @author Zereao
 * @version 2018/06/13/16:33
 */
public class ServerInboundHandler_1 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws InterruptedException {
        Number n = (Number) msg;
        System.out.println("ServerInboundHandler_1  ---- get num = " + n.getNum());
        n.add();
        TimeUnit.SECONDS.sleep(2);
//        ctx.fireChannelRead(n);
        ctx.writeAndFlush(n);
//        ctx.channel().writeAndFlush(n);
    }
}
