package com.anqy.xyz.jvm.chapter02;

import java.util.ArrayList;

/**
 * @Description: todo(Java堆溢出)
 * @Author: xiefu
 * @Date: 2019/3/25 10:52
 */
public class HeapOOM {
    static class OOMObject{

    }

    public static void main(String[] args) {
        ArrayList<OOMObject> list = new ArrayList<OOMObject>();
        while (true){
            list.add(new OOMObject());
        }
    }
}
