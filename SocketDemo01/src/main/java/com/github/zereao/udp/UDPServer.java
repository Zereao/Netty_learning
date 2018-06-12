package com.github.zereao.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author Zereao
 * @version 2018/06/12/16:47
 */
public class UDPServer {
    public static void main(String[] args) {
        try {
            new UDPServer().serverStart();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void serverStart() throws IOException {
        System.out.println("UDP服务端，启动~");
        DatagramSocket socket = new DatagramSocket(10010);
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        // 接收客户端发送的数据
        socket.receive(packet); // 此方法在接收数据报之前会一直阻塞
        // 接收数据
        String info = new String(data, 0, data.length);
        System.out.println("我是服务器，客户端说：" + info);

        // 向客户端响应数据
        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        byte[] data2 = "我是服务器，通过String.getBytes()获取到数据".getBytes();
        // 创建数据包报，包含响应的信息
        DatagramPacket respPacket = new DatagramPacket(data2, data2.length, address, port);
        // 响应客户端，向客户端发送信息
        socket.send(respPacket);
        // 关闭资源
        socket.close();
        System.out.println("UDP服务端，停止~");
    }
}
