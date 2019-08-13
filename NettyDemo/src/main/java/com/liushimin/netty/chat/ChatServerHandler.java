package com.liushimin.netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>标题: </p>
 * <p>功能描述: </p>
 *
 * <p>创建时间: 2019/8/12 16:27</p>
 * <p>作者：lshim</p>
 * <p>修改历史记录：</p>
 * ====================================================================<br>
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    public static List<Channel> channels = new ArrayList<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel inChannel = ctx.channel();
        channels.add(inChannel);
        System.out.println("[Server]:" + inChannel.remoteAddress().toString() + "上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel inChannel = ctx.channel();
        channels.remove(inChannel);
        System.out.println("[Server]:" + inChannel.remoteAddress().toString() + "离线");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel inChannel = ctx.channel();
        System.out.println("[" + inChannel.remoteAddress().toString() + "]说:" + msg);
        for (Channel channel : channels) {
            if (channel != inChannel) {
                channel.writeAndFlush("[" + inChannel.remoteAddress().toString() + "]说:" + msg);
            }
        }
    }
}
