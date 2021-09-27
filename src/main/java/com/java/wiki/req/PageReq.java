package com.java.wiki.req;

public class PageReq {
    private int num;

    private int size;


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    @Override
    public String toString() {
        return "PageReq{" +
                "num=" + num +
                ", size=" + size +
                '}';
    }
}