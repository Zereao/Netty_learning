package com.github.zereao.netty.demo02;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @author Zereao
 * @version 2018/06/13/16:12
 */
@SuppressWarnings("FieldCanBeLocal")
public class Client {
    private String host = "127.0.0.1";
    private int port = 10010;

    private void start() {
        EventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        // 这里一定要加入这两个类，用来给Objcet编码、解码，如果没有，就无法传递对象
                        // 并且，实体类(例如这里的Number类)要实现Serializable接口，否则也无法传输
                        socketChannel.pipeline().addLast(new ObjectEncoder());
                        socketChannel.pipeline().addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.weakCachingConcurrentResolver(null))); // 最大长度
                        socketChannel.pipeline().addLast(new ClientHandler_1());
                    }
                });
        try {
            Number number = new Number();
            number.setNum(1);
            ChannelFuture future = bootstrap.connect(host, port).sync();
            future.channel().writeAndFlush(number);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new Client().start();
    }

}
