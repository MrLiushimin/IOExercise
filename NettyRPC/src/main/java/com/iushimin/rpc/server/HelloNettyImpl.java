package com.iushimin.rpc.server;

/**
 * <p>标题: </p>
 * <p>功能描述: </p>
 *
 * <p>创建时间: 2019/8/13 14:15</p>
 * <p>作者：lshim</p>
 * <p>修改历史记录：</p>
 * ====================================================================<br>
 */
public class HelloNettyImpl implements HelloNetty {
    @Override
    public String hello() {
        return "Hello Netty";
    }
}
