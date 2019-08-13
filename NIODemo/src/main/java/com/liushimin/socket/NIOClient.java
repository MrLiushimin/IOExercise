package com.liushimin.socket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * <p>标题: </p>
 * <p>功能描述: </p>
 *
 * <p>创建时间: 2019/8/7 18:01</p>
 * <p>作者：lshim</p>
 * <p>修改历史记录：</p>
 * ====================================================================<br>
 */
public class NIOClient {
    public static void main(String[] args) throws Exception {
        //得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        //配置非阻塞
        socketChannel.configureBlocking(false);
        //提供服务器地址和端口
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 9999);
        if (!socketChannel.connect(address)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("Client:连接服务器端的同事,我还可以干点别的事情");
            }
        }
        String msg = "Hello,Server";
        socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
        System.in.read();

    }
}
