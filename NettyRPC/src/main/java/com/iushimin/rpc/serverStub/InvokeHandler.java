package com.iushimin.rpc.serverStub;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>标题: </p>
 * <p>功能描述: </p>
 *
 * <p>创建时间: 2019/8/13 14:53</p>
 * <p>作者：lshim</p>
 * <p>修改历史记录：</p>
 * ====================================================================<br>
 */
public class InvokeHandler extends ChannelInboundHandlerAdapter {
    public static List<Channel> channels = new ArrayList<>();

    @Override  //通道就绪
    public void channelActive(ChannelHandlerContext ctx)  {
        Channel inChannel=ctx.channel();
        channels.add(inChannel);
        System.out.println("[Server]:"+inChannel.remoteAddress().toString().substring(1)+"上线");
    }
    @Override  //通道未就绪
    public void channelInactive(ChannelHandlerContext ctx)  {
        Channel inChannel=ctx.channel();
        channels.remove(inChannel);
        System.out.println("[Server]:"+inChannel.remoteAddress().toString().substring(1)+"离线");
    }

    //得到接口类名字
    private String getImplClassName(ClassInfo classInfo) throws ClassNotFoundException {
        //服务接口和实现类所在包路径
        String interfacePath = "com.iushimin.rpc.server";
        int lastDot = classInfo.getClassName().lastIndexOf(".");
        String interfaceName = classInfo.getClassName().substring(lastDot);
        Class superClass = Class.forName(interfacePath + interfaceName);
        Reflections reflections = new Reflections(interfacePath);

        //得到接口所有的实现类
        Set<Class> implClassSet = reflections.getSubTypesOf(superClass);
        if (implClassSet.size() == 0) {
            System.out.println("未找到实现类");
            return null;
        } else if (implClassSet.size() > 1) {
            System.out.println("找到多个实现类,未明确使用哪一个");
            return null;
        } else {
            Class[] classes = implClassSet.toArray(new Class[0]);
            return classes[0].getName();
        }

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ClassInfo classInfo = (ClassInfo) msg;
        Object clazz = Class.forName(getImplClassName(classInfo)).newInstance();
        Method method = clazz.getClass().getMethod(classInfo.getMethodName(), classInfo.getTypes());
        Object result = method.invoke(clazz, classInfo.getObjects());
        ctx.writeAndFlush(result);
    }
}
