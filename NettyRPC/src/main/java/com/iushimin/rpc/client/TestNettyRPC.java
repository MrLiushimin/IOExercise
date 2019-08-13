package com.iushimin.rpc.client;

import com.iushimin.rpc.clientStub.NettyRPCProxy;

/**
 * <p>标题: </p>
 * <p>功能描述: </p>
 *
 * <p>创建时间: 2019/8/13 15:58</p>
 * <p>作者：lshim</p>
 * <p>修改历史记录：</p>
 * ====================================================================<br>
 */
public class TestNettyRPC {
    public static void main(String[] args) {
        HelloNetty helloNetty = NettyRPCProxy.create(HelloNetty.class);
        String hello = helloNetty.hello();
        System.out.println(hello);

        HelloRPC helloRPC = NettyRPCProxy.create(HelloRPC.class);
        String rpc = helloRPC.hello("RPC");
        System.out.println(rpc);
    }
}
