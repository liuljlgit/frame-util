package com.cloud.common.simplequery;

import java.io.Serializable;
import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

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
    private SortedSet list;

    public IntervalEntity(String name, Collection list) {
        this.name = name;
        this.list = new TreeSet<>(list);
    }

    public <T> IntervalEntity(String name,T[] list) {
        this.name = name;
        this.list = new TreeSet<>();
        for(int i=0;i<list.length;i++){
            this.list.add(list[i]);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SortedSet getList() {
        return list;
    }

    public void setList(SortedSet list) {
        this.list = list;
    }
}
