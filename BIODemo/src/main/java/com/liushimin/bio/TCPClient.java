package com.liushimin.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * <p>标题: </p>
 * <p>功能描述: </p>
 *
 * <p>创建时间: 2019/8/7 15:25</p>
 * <p>作者：lshim</p>
 * <p>修改历史记录：</p>
 * ====================================================================<br>
 */
public class TCPClient {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        while (true) {
            Socket socket = new Socket("127.0.0.1", 9999);
            OutputStream os = socket.getOutputStream();
            System.out.println("请输入");
            String msg = sc.nextLine();
            os.write(msg.getBytes());

            InputStream is = socket.getInputStream();
            byte[] b = new byte[10];
            is.read(b);
            System.out.println("服务端说:" + new String(b).trim());
            socket.close();
        }
    }
}
