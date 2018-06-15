package com.github.zereao.netty.demo03.marshalling;

import com.github.zereao.netty.demo03.marshalling.dto.Request;
import com.github.zereao.netty.demo03.marshalling.dto.Response;
import com.github.zereao.netty.demo03.marshalling.util.GzipUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author Zereao
 * @version 2018/06/13/19:23
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Request request = (Request) msg;
        System.out.println("Server:" + request.getId() + "," + request.getName() + "," + request.getMessage());

        Response response = new Response();
        response.setId(request.getId());
        response.setName("response " + request.getId());
        response.setRespMessage("响应内容：" + request.getMessage());
        byte[] unGizpData = GzipUtils.unGzip(request.getAttachment());
//        char separator = File.separatorChar;
//        FileOutputStream outputStream = new FileOutputStream(System.getProperty("user.dir") + separator + "recieve" + separator + "1.png");
        FileOutputStream outputStream = new FileOutputStream("1.png");
        outputStream.write(unGizpData);
        outputStream.flush();
        outputStream.close();
        ctx.writeAndFlush(response);
    }
}
