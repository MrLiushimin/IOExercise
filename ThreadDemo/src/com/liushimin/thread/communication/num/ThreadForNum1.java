package com.liushimin.thread.communication.num;

/**
 * <p>标题: </p>
 * <p>功能描述: </p>
 *
 * <p>创建时间: 2019/8/7 14:58</p>
 * <p>作者：lshim</p>
 * <p>修改历史记录：</p>
 * ====================================================================<br>
 */
public class ThreadForNum1 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (MyLock.o) {
                System.out.println(1);
                MyLock.o.notify();
                try {
                    MyLock.o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
