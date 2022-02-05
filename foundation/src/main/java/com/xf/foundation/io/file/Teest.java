package com.xf.foundation.io.file;

/**
 * @Description: todo(描述)
 * @Author: xiefu
 * @Date: 2021/11/27 22:23
 */
public class Teest {
    public static void main(String[] args) {
        String s = "dfs#ewrw";
        String[] split = s.split("#");
        String str  = split[0];
        int length = split.length;
        System.out.println(str);
    }
}
