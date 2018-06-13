package com.github.zereao.netty.demo02;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @author Zereao
 * @version 2018/06/13/16:31
 */
public class Server {
    private void run() {
        // 下面这种写法，很久不见还觉得很陌生
        EventLoopGroup bossGroup = new NioEventLoopGroup(),
                workGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        // 这个Object转换，如果不放在前面，则发送的时候照不到out
                        socketChannel.pipeline().addLast(new ObjectEncoder());
                        socketChannel.pipeline().addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.weakCachingConcurrentResolver(null)));
                        socketChannel.pipeline().addLast(new ServerOutboundHandler_1());
                        socketChannel.pipeline().addLast(new ServerOutboundHandler_2());
                        socketChannel.pipeline().addLast(new ServerInboundHandler_1());
                        socketChannel.pipeline().addLast(new ServerInboundHandler_2());
                    }
                });
        try {
            ChannelFuture future = bootstrap.bind(10010).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new Server().run();
    }
}
