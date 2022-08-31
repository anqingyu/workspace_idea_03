package com.xf.algorithm.writtenexamination;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: todo(全排列的递归算法Java实现过程)
 * 1、一个数的全排列，如排列{1}，就是这个数本身这一种情况
 * 2、两个数的全排列，如排列{1,2}：
 *  第一步：将{1}放在第零个位置，剩下的{2}进行一个数的全排列，结果为{1,2}
 *  第二步：将{2}放在第零个位置，剩下的{1}进行一个数的全排列，结果为{2,1}
 *  即两个数的全排列为以上2种情况。
 * 3、三个数的全排列，如排列{1,2,3}：
 *  第一步：将{1}放在第零个位置，剩下的{2,3}进行两个数的全排列，结果为{1,2,3}  {1,3,2}
 *  第二步：将{2}放在第零个位置，剩下的{1,3}进行两个数的全排列，结果为{2,1,3}  {2,3,1}
 *  第三步：将{3}放在第零个位置，剩下的{1,2}进行两个数的全排列，结果为{3,1,2}  {3,2,1}
 *  即三个数的全排列为以上6种情况。
 * 4、即m个数（无重）的全排列，就是将m个数分别放在第零个位置，再将剩下的m-1个数的全排列加在后面，当 m-1 = 1 时为递归的出口。
 * @Author: xiefu
 * @Date: 2021/1/16 20:42
 */
public class Permutation {

    /**
     * 无重复项
     * @param arr 为待排列的数组
     * @param n 标记数组当前位置，即n=0时，对arr[0]到arr[length-1]全排列；n=1时，对arr[1]到arr[length-1]全排列...以此类推，length为数组长度
     */
    private static void test1(int[] arr , int n) {
        int length = arr.length;
        // 当n定位到最后一个数时，即到递归出口
        if(n>=length-1) {
            for(int i:arr) {
                System.out.print(i);
            }
            System.out.println();
        }else {
            // 将length-n个数分别放在第n个位置，(length-n)对于上文分析中的m
            for(int i=n;i<length;i++) {
                // 以交换位置的方式实现
                {int temp = arr[n]; arr[n] = arr[i]; arr[i] = temp;}
                // 对剩下的length-n-1个数全排列
                test1(arr,n+1);
                // 恢复原来的顺序，进行下一次交换
                {int temp = arr[n]; arr[n] = arr[i]; arr[i] = temp;}
            }
        }
    }

    /**
     * 有重复项的全排列：基本思路与无重复项的全排列一致。只不过应保证第零个数不能重复
     * @param arr
     * @param n
     */
    private static void test2(int arr[] , int n) {
        int length = arr.length;
        //存放第零个位置已经有过的数字
        List<Integer> exist = new ArrayList<Integer>();
        if(n>=length-1) {
            for(int i:arr) {System.out.print(i);}
            System.out.println();
        }else {
            for(int i=n;i<length;i++) {
                //如果arr[i]不在exis中，则可以把arr[i]放在第零个位置
                if(!exist.contains(arr[i])) {
                    //将arr[i]放进exist
                    exist.add(arr[i]);
                    {int temp = arr[n]; arr[n] = arr[i]; arr[i] = temp;}
                    test2(arr,n+1);
                    {int temp = arr[n]; arr[n] = arr[i]; arr[i] = temp;}
                }
            }
        }
    }


    public static void main(String args[]) {
        // 无重复项的全排列
        int[] arr = {1,2,3,4};
        test1(arr,0);
        System.out.println("无重复项的全排列执行完毕=============");
        // 有重复项的全排列
        int[] arr2 = {1,2,2,4};
        test2(arr2,0);
        System.out.println("有重复项的全排列执行完毕=============");
    }

}
