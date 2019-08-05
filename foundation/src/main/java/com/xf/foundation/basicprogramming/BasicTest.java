package com.xf.foundation.basicprogramming;

import org.junit.jupiter.api.Test;

/**
 * @Description: 基础编程代码测试
 * @Author: xiefu
 * @Date: 2019/7/2 14:57
 */
public class BasicTest{

    /**
     * 题目：古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第三个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子对数为多少？
     *
     * 程序分析： 兔子的规律为数列1,1,2,3,5,8,13,21....
     */
    @Test
    public void fibonacciSequence(){
        int n = 10;
        System.out.println("第"+n+"个月兔子总数位"+Basic.fibonacciSequence(10));
    }

    /**
     * 题目：判断101-200之间有多少个素数，并输出所有素数。
     *
     * 程序分析：判断素数的方法：用一个数分别去除2到sqrt(这个数)，如果能被整除，则表明此数不是素数，反之是素数。
     */
    @Test
    public void primeNumber(){
        int m  = 101;
        int n = 200;
        int count = 0;
        // 统计素数个数
        for (int i = m; i < n; i++) {
            if(Basic.isPrime(i)){
                count ++;
                System.out.print(i+"\t");
                if(count%10 == 0){
                    System.out.println();
                }
            }
        }

        System.out.println();
        System.out.println("在"+m+"和"+n+"之间共有"+count+"个素数");
    }

    /**
     * 题目：打印出所有的"水仙花数"，所谓"水仙花数"是指一个三位数，其各位数字立方和等于该数本身。例如：153是一个"水仙花数"，因为153=1的三次方＋5的三次方＋3的三次方。
     *
     * 程序分析：利用for循环控制100-999个数，每个数分解出个位，十位，百位。
     */
    @Test
    public void id(){
        for (int i = 100; i < 1000; i++) {
            if(Basic.isNarcissisticNumber(i)){
                System.out.print(i+" ");
            }
        }
    }

    /**
     * 题目：将一个正整数分解质因数。例如：输入90,打印出90=2*3*3*5。
     *
     * 程序分析：对n进行分解质因数，应先找到一个最小的质数k，然后按下述步骤完成：
     *  (1)如果这个质数恰等于n，则说明分解质因数的过程已经结束，打印出即可。
     *  (2)如果n<>k，但n能被k整除，则应打印出k的值，并用n除以k的商,作为新的正整数n,重复执行第一步。
     *  (3)如果n不能被k整除，则用k+1作为k的值,重复执行第一步。
     */
    @Test
    public void decompose(){
        for (int i = 7; i < 13; i ++){
            Basic.decompose(i);
        }
    }
}
