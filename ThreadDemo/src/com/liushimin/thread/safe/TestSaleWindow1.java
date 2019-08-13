package com.liushimin.thread.safe;

/**
 * <p>标题: </p>
 * <p>功能描述: </p>
 *
 * <p>创建时间: 2019/8/7 14:33</p>
 * <p>作者：lshim</p>
 * <p>修改历史记录：</p>
 * ====================================================================<br>
 */
public class TestSaleWindow1 {

    public static void main(String[] args) {
        SaleWindow1 sw = new SaleWindow1();

        Thread thread1 = new Thread(sw);
        Thread thread2 = new Thread(sw);

        thread1.setName("窗口A");
        thread2.setName("窗口B");

        thread1.start();
        thread2.start();
    }
}
