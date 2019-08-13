package com.liushimin.thread.communication.num;

/**
 * <p>标题: </p>
 * <p>功能描述: </p>
 *
 * <p>创建时间: 2019/8/7 15:03</p>
 * <p>作者：lshim</p>
 * <p>修改历史记录：</p>
 * ====================================================================<br>
 */
public class TestThreadForNum {

    public static void main(String[] args) {
        ThreadForNum1 t1 = new ThreadForNum1();
        ThreadForNum2 t2 = new ThreadForNum2();
        t1.start();
        t2.start();
    }
}
