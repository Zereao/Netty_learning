package com.github.zereao.udp;

import java.io.IOException;
import java.net.*;

/**
 * @author Zereao
 * @version 2018/06/12/16:56
 */
public class UDPClient {
    public static void main(String[] args) {
        try {
            new UDPClient().clientStart();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clientStart() throws IOException {
        System.out.println("UDP客户端，启动~");
        InetAddress address = InetAddress.getByName("localhost");
        int port = 10010;
        byte[] data = "客户端信息.getBytes()得到数据".getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
        DatagramSocket socket = new DatagramSocket();
        // 向服务器发送数据
        socket.send(packet);

        // 接收服务器的响应
        byte[] respData = new byte[1024];
        DatagramPacket respPacket = new DatagramPacket(respData, respData.length);
        socket.receive(respPacket);
        String reply = new String(respData, 0, respPacket.getLength());
        System.out.println("我是客户端，服务器说：" + reply);
        socket.close();
        System.out.println("UDP，客户端，停止");
    }
}
