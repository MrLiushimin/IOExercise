package com.liushimin.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * <p>标题: </p>
 * <p>功能描述: 聊天程序服务端</p>
 *
 * <p>创建时间: 2019/8/8 16:30</p>
 * <p>作者：lshim</p>
 * <p>修改历史记录：</p>
 * ====================================================================<br>
 */
public class ChatServer {

    private ServerSocketChannel listenerChannel;//监听通道
    private Selector selector;//选择器对象
    private static final int PORT = 9999;//服务器端口
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //构造方法进行初始化
    public ChatServer() throws IOException {
        //得到监听通道
        listenerChannel = ServerSocketChannel.open();
        //得到选择器
        selector = Selector.open();
        //绑定端口
        listenerChannel.bind(new InetSocketAddress(PORT));
        //设置为非阻塞模式
        listenerChannel.configureBlocking(false);
        //将选择器与监听通道绑定,并监听accept事件
        listenerChannel.register(selector, SelectionKey.OP_ACCEPT);

    }

    //进行干活
    public void start() {
        try {
            while (true) {//不停监控
                if (selector.select(2000) == 0) {
                    System.out.println("Server:没有客户端找我,我就干别的事情");
                    continue;
                }
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()) {//连接请求事件
                        SocketChannel sc = listenerChannel.accept();
                        sc.configureBlocking(false);
                        sc.register(selector, SelectionKey.OP_READ);
                        System.out.println(sc.getRemoteAddress().toString() + "上线了...");
                    }
                    if (key.isReadable()) {//读取事件
                        recvMsg(key);
                    }
                    //防止重复处理
                    iterator.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //接收客户端消息,并广播出去
    public void recvMsg(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int size = channel.read(buffer);
        if (size > 0) {
            String msg = new String(buffer.array());
            printInfo(msg.trim());
            //进行广播
            broadCast(channel, msg);
        }
    }

    //给所有客户端进行广播
    public void broadCast(SocketChannel except, String msg) throws IOException {
        System.out.println("服务器进行广播...");
        for (SelectionKey key : selector.keys()) {
            Channel targetChannel = key.channel();
            if (targetChannel instanceof SocketChannel && targetChannel != except) {
                SocketChannel channel = (SocketChannel) targetChannel;
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                channel.write(buffer);
            }
        }
    }

    private void printInfo(String str) {
        System.out.println("[" + sdf.format(new Date()) + "]->" + str);
    }

    //开启服务器
    public static void main(String[] args) throws IOException {
        new ChatServer().start();
    }
}
