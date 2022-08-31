package com.xf.foundation.basicprogramming;

/**
 * @Description: 基础编程代码
 * @Author: xiefu
 * @Date: 2019/7/2 15:34
 */
public class Basic {

    /**
     * 计算斐波那契数列的值
     * @param n
     * @return
     */
    public static int fibonacciSequence(int n){
        if(n == 1 || n == 2){
            return 1;
        }
        return fibonacciSequence(n-1) + fibonacciSequence(n-2);
    }

    /**
     * 判断入参是否是素数
     * @param n 入参
     * @return
     */
    public static boolean isPrime(int n){
        boolean flag = true;
        if(n == 1){
            flag = false;
        } else {
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if((n%i) == 0 || n == 1){
                    flag = false;
                    break;
                } else {
                    flag = true;
                }
            }
        }
        return flag;
    }

    /**
     * 判断是否是水仙花数
     * @param lotus 入参
     * @return
     */
    public static boolean isNarcissisticNumber(int lotus){
        int n = lotus;
        int m = n/100;
        n -= m*100;
        int sum  = m*m*m;
        m = n/10;
        n -= m*10;
        sum += m*m*m + n*n*n;
        if(sum == lotus){
            return true;
        }else {
            return false;
        }

    }

    /**
     * 将一个正整数分解质因数
     * @param n
     */
    public static void decompose(int n){
        System.out.print(n+"=");
        for (int i = 2; i < n +1; i++) {
            while (n % i == 0 && n != i){
                n /= i;
                System.out.print(i + "*");
            }
            if(n==i){
                System.out.println(i);
                break;
            }
        }
    }
}
