package com.liushimin.socket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * <p>标题: </p>
 * <p>功能描述: NIO网络服务器服务端</p>
 *
 * <p>创建时间: 2019/8/7 16:16</p>
 * <p>作者：lshim</p>
 * <p>修改历史记录：</p>
 * ====================================================================<br>
 */
public class NIOServer {
    public static void main(String[] args) throws Exception {
        //得到一个ServerSocketChannel对象 老大
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //得到一个Selector对象 间谍
        Selector selector = Selector.open();
        //绑定端口号
        ssc.bind(new InetSocketAddress(9999));
        //设置非阻塞方式
        ssc.configureBlocking(false);
        //把ServerSocket对象注册给Selector对象
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        //干活
        while (true) {
            //监控客户端
            if (selector.select(2000) == 0) {
                System.out.println("Server:没有客户端搭理我,我就干别的事情");
                continue;
            }

            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if (key.isAcceptable()) {//客户端连接请求事件
                    //客户端连接事件
                    System.out.println("OP_ACCEPT");
                    SocketChannel socketChannel = ssc.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (key.isReadable()) {//读取客户端数据事件
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    channel.read(buffer);
                    System.out.println("客户端发来数据:" + new String(buffer.array()));
                }
                //手动从集合中移除当前key,放置重复处理
                keyIterator.remove();
            }
        }
    }
}
