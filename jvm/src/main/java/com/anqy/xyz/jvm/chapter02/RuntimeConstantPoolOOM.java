package com.anqy.xyz.jvm.chapter02;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: todo(运行时常量池溢出)
 * @Author: xiefu
 * @Date: 2019/3/29 21:29
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        // 使用List保持着常量池引用，避免Full GC回收常量池行为
        List<String> list = new ArrayList<String>();
    }

}
