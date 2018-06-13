package com.github.zereao.netty.demo01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author Zereao
 * @version 2018/06/13/11:32
 */
public class Server {
    private int port = 10060;

    public Server(int port) {
        this.port = port;
    }

    public void run() {
        // 这一个EventLoopGroup 用于处理服务器端接收客户端连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 进行网络通信、读写
        EventLoopGroup workerGrooup = new NioEventLoopGroup();

        try {
            // 引导类，用于配置服务器通道的一些属性
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGrooup) // 绑定两个线程组
                    .channel(NioServerSocketChannel.class) //指定NIO的模式
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ServerHandler());
                            // 这里可以写多个 addLast;
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128) //设置TCP缓冲区
                    .option(ChannelOption.SO_SNDBUF, 32 * 1024) // 设置发送数据缓冲大小
                    .option(ChannelOption.SO_RCVBUF, 32 * 1024) //设置接收数据缓冲大小
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // 保持连接
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGrooup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new Server(10060).run();
    }
}
