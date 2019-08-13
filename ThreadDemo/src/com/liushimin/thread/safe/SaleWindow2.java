package com.liushimin.thread.safe;

/**
 * <p>标题: </p>
 * <p>功能描述: </p>
 *
 * <p>创建时间: 2019/8/7 14:24</p>
 * <p>作者：lshim</p>
 * <p>修改历史记录：</p>
 * ====================================================================<br>
 */
public class SaleWindow2 implements Runnable {

    private int id = 10;

    public synchronized void sale() {
        if (id > 0) {
            System.out.println(Thread.currentThread().getName() + ":卖了编号为" + id + "的火车票");
            id--;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            sale();
        }
    }
}
