package com.github.zereao.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 多线程使用
 *
 * @author Zereao
 * @version 2018/06/12/16:24
 */
public class MultiServer {
    public static void main(String[] args) throws IOException {
        new MultiServer().MultiServerStart();
    }

    public void MultiServerStart() throws IOException {
        System.out.println("多线程服务端，开始监听....");
        ServerSocket serverSocket = new ServerSocket(10087);
        Socket socket = null;
        // 服务器数量
        int serverNum = 0;
        while (true) {
            socket = serverSocket.accept();
            ServerThread serverThread = new ServerThread(socket);
            serverThread.start();
            serverNum++;
            System.out.println("客户端连接数量 = " + serverNum);
        }
    }

}

class ServerThread extends Thread {
    private Socket socket = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("多线程 run方法，开始监听....");
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
            System.out.println("服务端关闭。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
