package com.liushimin.netty.codec;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

/**
 * <p>标题: </p>
 * <p>功能描述: </p>
 *
 * <p>创建时间: 2019/8/12 17:30</p>
 * <p>作者：lshim</p>
 * <p>修改历史记录：</p>
 * ====================================================================<br>
 */
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        //boss线程组,接收客户端连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //工作线程组,处理网络操作
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        //创建服务器端,并配置
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)//设置线程组
                .channel(NioServerSocketChannel .class)//设置服务器通道实现类
                .option(ChannelOption.SO_BACKLOG, 128)//设置线程队列中的等待连接的个数
                .childOption(ChannelOption.SO_KEEPALIVE, true)//保持活动连接状态
                .childHandler(new ChannelInitializer<SocketChannel>() {//创建一个通道初始化对象

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast("decoder", new ProtobufDecoder(BookMessage.Book.getDefaultInstance()));
                        ch.pipeline().addLast(new NettyServerHandler());
                    }
                });
        System.out.println("...........Server is ready...........");
        //绑定端口,bind为异步方法,sync为同步阻塞
        ChannelFuture future = serverBootstrap.bind(9999).sync();
        System.out.println("...........Server is starting...........");

        //关闭通道,关闭线程组
        future.channel().closeFuture().sync();//异步

        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
        System.out.println("...........Server is stoping...........");
    }
}
