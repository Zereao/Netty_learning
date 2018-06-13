package com.github.zereao.netty.demo01;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author Zereao
 * @version 2018/06/13/14:39
 */
public class Client {
    private void start() throws InterruptedException {
        EventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new ClientHandler());
                    }
                });
        ChannelFuture future = bootstrap.connect("127.0.0.1", 10060).sync();
        future.channel().writeAndFlush("测试".getBytes());
        future.channel().closeFuture().sync();
        workGroup.shutdownGracefully();
    }

    public static void main(String[] args) {
        try {
            new Client().start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
