package com.cloud.common.simplequery;

import java.io.Serializable;

/**
 * 用来生成sql中的in和not in操作的对象
 */
public class IntervalEntity implements Serializable {
    /**
     * field comment: 名称
     */
    private String name;

    /**
     * field comment: 在in或者not in中的值
     */
    private Object list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getList() {
        return list;
    }

    public void setList(Object list) {
        this.list = list;
    }
}
