package com.liushimin.thread.communication.farmerchild;

/**
 * <p>标题: </p>
 * <p>功能描述: </p>
 *
 * <p>创建时间: 2019/8/7 15:07</p>
 * <p>作者：lshim</p>
 * <p>修改历史记录：</p>
 * ====================================================================<br>
 */
public class Child extends Thread {

    @Override
    public void run() {
        while (true) {
            synchronized (Kuang.kuang) {
                if (Kuang.kuang.size() == 0) {
                    try {
                        Kuang.kuang.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Kuang.kuang.remove("apple");
                System.out.println("小孩吃了一个水果,目前筐里有" + Kuang.kuang.size() + "个水果");

                Kuang.kuang.notify();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
