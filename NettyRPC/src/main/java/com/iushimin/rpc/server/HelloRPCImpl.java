package com.iushimin.rpc.server;

/**
 * <p>标题: </p>
 * <p>功能描述: </p>
 *
 * <p>创建时间: 2019/8/13 14:16</p>
 * <p>作者：lshim</p>
 * <p>修改历史记录：</p>
 * ====================================================================<br>
 */
public class HelloRPCImpl implements HelloRPC {
    @Override
    public String hello(String name) {
        return "Hello " + name;
    }
}
