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
public class TestSaleWindow2 {

    public static void main(String[] args) {
        SaleWindow2 sw = new SaleWindow2();

        Thread thread1 = new Thread(sw);
        Thread thread2 = new Thread(sw);

        thread1.setName("窗口A");
        thread2.setName("窗口B");

        thread1.start();
        thread2.start();
    }
}
