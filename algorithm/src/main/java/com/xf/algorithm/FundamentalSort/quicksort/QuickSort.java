package com.xf.algorithm.FundamentalSort.quicksort;

/**
 * @Description: todo(快速排序)
 * @Author: xiefu
 * @Date: 2019/2/26 23:15
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] testArray = new int[]{2, 8, 7, 1, 3, 5, 6, 4};
        sort(testArray);
        System.out.println(testArray);
        // 从任意位置开始排序
        int[] testArray2 = new int[]{2, 8, 7, 1, 3, 5, 6, 4};
        sort(testArray2, 3, testArray2.length - 1);
        System.out.println(testArray2);
    }

    public static void sort(int[] a) {
        //StdRandom.shuffle(a); //打乱输入数组的元素间的相对顺序，避免出现最坏情况
        sort(a, 0, a.length - 1);
    }

    public static void  sort(int[] A, int p, int r){
        if(p < r){
            int q = partition(A, p, r);
            sort(A, p, q-1);
            sort(A, q + 1, r);
        }
    }

    private static int partition(int[] A, int p, int r) {
        int x = A[r];
         int i = p - 1;
        int j = p;
        while(j <= r-1){
            if(A[j] <= x){
                i = i + 1;
                exchange(A, i, j);
            }
            j ++;
        }
        exchange(A, i+1, r);
        return i + 1;
    }

    public static void exchange(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
