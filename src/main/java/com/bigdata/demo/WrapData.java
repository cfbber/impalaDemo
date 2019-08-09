package com.bigdata.demo;

import java.io.Serializable;
import java.util.List;

public class WrapData implements Serializable{
    private List<User> list;
    private Integer time;

    @Override
    public String toString() {
        return "WrapData{" +
                "list=" + list +
                ", time=" + time +
                '}';
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
