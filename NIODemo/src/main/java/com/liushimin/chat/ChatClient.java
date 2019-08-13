package com.liushimin.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;


/**
 * <p>标题: </p>
 * <p>功能描述: 聊天程序客户端</p>
 *
 * <p>创建时间: 2019/8/8 16:30</p>
 * <p>作者：lshim</p>
 * <p>修改历史记录：</p>
 * ====================================================================<br>
 */
public class ChatClient {

    private final String HOST = "127.0.0.1";//服务器地址
    private int PORT =9999;//端口号
    private SocketChannel channel;//网络通道
    private String userName;//聊天用户名


    //构造函数进行初始化
    public ChatClient() throws IOException {
        //得到一个网络通道
        channel = SocketChannel.open();
        //设置非阻塞方式
        channel.configureBlocking(false);
        //获取服务器端IP与端口
        InetSocketAddress address = new InetSocketAddress(HOST, PORT);
        if (!channel.connect(address)) {
            while (!channel.finishConnect()) {
                System.out.println("Client:连接服务器的同时,我还可以干点别的事情");
            }
        }

        userName = channel.getLocalAddress().toString();
        System.out.println("-------------Client(" + userName + ")is ready---------------------");
    }

    //客户端发送消息
    public void sendMsg(String msg) throws IOException {
        if ("bye".equalsIgnoreCase(msg)) {
            channel.close();
            return;
        }
        msg = userName + "说:" + msg;
        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
        channel.write(buffer);
    }

    //客户端接收消息
    public void recvMsg() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int size = channel.read(buffer);
        if (size > 0) {
            System.out.println(new String(buffer.array()).trim());
        }
    }

    public static void main(String[] args) throws IOException {
        ChatClient client = new ChatClient();

        new Thread(){
            @Override
            public void run() {
                while (true) {
                    try {
                        client.recvMsg();
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String msg = sc.nextLine();
            client.sendMsg(msg);
        }
    }
}
