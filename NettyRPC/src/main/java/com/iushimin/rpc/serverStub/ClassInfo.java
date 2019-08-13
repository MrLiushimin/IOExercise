package com.iushimin.rpc.serverStub;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>标题: </p>
 * <p>功能描述: </p>
 *
 * <p>创建时间: 2019/8/13 14:17</p>
 * <p>作者：lshim</p>
 * <p>修改历史记录：</p>
 * ====================================================================<br>
 */
@Getter
@Setter
public class ClassInfo implements Serializable {

    private static final long serialVersionUID = 5363707052480187886L;
    private String className;//类名
    private String methodName;//方法名
    private Class<?>[] types;//参数类型
    private Object[] objects;//参数列表
}
