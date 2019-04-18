package com.xf.algorithm.chapter02;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * @Description: todo(排序算法类的模板)
 * @Author: xiefu
 * @Date: 2018/12/14 14:01
 */
public class Example {

    public static void sort(Comparable[] a){

    }

    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a){
        // 打印数组
        for (int i = 0; i < a.length; i++){
            StdOut.println(a[i] + " ");
        }
    }

    public static boolean isSorted(Comparable[] a){
        // 测试数组元素是否有序
        for (int i = 1; i < a.length; i ++){
            if(less(a[i], a[i-1])){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        // 从标准输入读取字符串，将他们排序并输出
        String[] a = In.readStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
