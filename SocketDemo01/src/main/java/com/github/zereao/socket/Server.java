package com.github.zereao.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Zereao
 * @version 2018/06/12/15:48
 */
public class Server {
    public static void main(String[] args) {
        try {
            new Server().setverStart();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setverStart() throws IOException {
        System.out.println("服务端启动，监听中.....");
        ServerSocket serverSocket = new ServerSocket(10086); // 绑定端口为10086
        // 调用accept方法开始监听，等待客户端的连接
        Socket socket = serverSocket.accept();
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String info = "";
        while ((info = br.readLine()) != null) {
            System.out.println("我是服务器，客户端说：" + info);
        }
        //关闭输入流
        socket.shutdownInput();
        //获取输出流，响应客户端的请求
        OutputStream os = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(os);
        pw.write("服务器说，HelloWorld");
        pw.flush();

        pw.close();
        os.close();
        br.close();
        isr.close();
        is.close();
        socket.close();
        serverSocket.close();
        System.out.println("服务端关闭。");
    }
}
