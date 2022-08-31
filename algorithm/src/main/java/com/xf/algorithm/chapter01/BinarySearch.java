package com.xf.algorithm.chapter01;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * @Description: todo(二分搜索法:查询前需要用sort()方法将数组排序，如果数组没有排序，则结果是不确定的)
 * @Author: xiefu
 * @Date: 2018/12/13 14:07
 */
public class BinarySearch {

    /**
     * @param key
     * @param arr :arr必须是有序数组
     * @return
     */
    public static int rank(int key, int[] arr){
        int lo = 0;
        int hi = arr.length -1;
        while(lo <= hi){
            int mid = lo + (hi - lo)/2;
            if(key < arr[mid]){
                hi = mid - 1;
            }else if (key > arr[mid]){
                lo = mid + 1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] whitelist = In.readInts(args[0]);
        Arrays.sort(whitelist);
        while (!StdIn.isEmpty()){
            // 读取键值，如果不存在于白名单中则将其打印
            int key = StdIn.readInt();
            if(rank(key, whitelist) < 0){
                StdOut.print(key);
            }
        }

        String s = "a-b";
        String[] split = s.split("-");
        String a = "3.45";
        for (String s1:split) {
            System.out.println(s1);
        }
        Double aDouble = Double.valueOf(a);
        System.out.println(aDouble*100);
    }
}
