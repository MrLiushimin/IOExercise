package com.liushimin.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * <p>标题: </p>
 * <p>功能描述: </p>
 *
 * <p>创建时间: 2019/8/7 15:24</p>
 * <p>作者：lshim</p>
 * <p>修改历史记录：</p>
 * ====================================================================<br>
 */
public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("服务端准备就绪");
            Socket socket = serverSocket.accept();
            System.out.println("连接到客户端");
            InputStream is = socket.getInputStream();
            byte[] b = new byte[10];



            is.read(b);
            String clientIP = socket.getInetAddress().getHostAddress();
            System.out.println(clientIP + ":" + socket.getPort() + "说:" + new String(b).trim());
            System.out.println("请回复");
            String s = sc.nextLine();
            OutputStream os = socket.getOutputStream();
            os.write(s.getBytes());
            os.flush();
        }
    }
}
