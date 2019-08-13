package com.liushimin.netty.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * <p>标题: </p>
 * <p>功能描述: </p>
 *
 * <p>创建时间: 2019/8/12 17:30</p>
 * <p>作者：lshim</p>
 * <p>修改历史记录：</p>
 * ====================================================================<br>
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        BookMessage.Book book = (BookMessage.Book) msg;
        System.out.println("客户端发来数据:" + book.getName());
    }
}
