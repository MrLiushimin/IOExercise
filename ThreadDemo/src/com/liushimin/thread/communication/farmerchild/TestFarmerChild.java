package com.liushimin.thread.communication.farmerchild;

/**
 * <p>标题: </p>
 * <p>功能描述: </p>
 *
 * <p>创建时间: 2019/8/7 15:08</p>
 * <p>作者：lshim</p>
 * <p>修改历史记录：</p>
 * ====================================================================<br>
 */
public class TestFarmerChild {
    public static void main(String[] args) {
        Farmer farmer = new Farmer();
        Child child = new Child();

        farmer.start();
        child.start();
    }
}
