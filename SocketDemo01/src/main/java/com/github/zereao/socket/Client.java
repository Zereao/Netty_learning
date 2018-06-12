package com.github.zereao.socket;

import java.io.*;
import java.net.Socket;

/**
 * @author Zereao
 * @version 2018/06/12/15:47
 */
public class Client {
    public static void main(String[] args) {
        try {
            new Client().clientStart();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clientStart() throws IOException {
        System.out.println("客户端启动~");
        // 创建客户端Socket，指定服务器地址和端口
        Socket socket = new Socket("localhost", 10087);
        // 获取输出流，向服务器发送信息
        OutputStream os = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(os);
        pw.write("客户端说：用户Admin，密码Pass");
        pw.flush();
        socket.shutdownOutput();
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String info = "";
        while ((info = br.readLine()) != null) {
            System.out.println("我是客户端，服务器说：" + info);
        }
        br.close();
        is.close();
        pw.close();
        os.close();
        socket.close();
        System.out.println("客户端关闭");
    }
}
