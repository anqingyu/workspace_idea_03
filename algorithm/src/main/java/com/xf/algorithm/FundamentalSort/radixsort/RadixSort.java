package com.xf.algorithm.FundamentalSort.radixsort;

/**
 * @Description: 基数排序:对数组{53, 3, 542, 748, 14, 214, 154, 63, 616}进行排序
 *          步骤：首先将所有待比较树脂统一为统一位数长度，接着从最低位开始，依次进行排序。
 *              1. 按照个位数进行排序。
 *              2. 按照十位数进行排序。
 *              3. 按照百位数进行排序。
 *          排序后，数列就变成了一个有序序列
 * @Author: xiefu
 * @Date: 2019/3/5 23:56
 */
public class RadixSort {

    /**
     * 获取数组中的最大值
     */
    private static int getMax(int[] arr){
        if(arr == null){
            throw  new RuntimeException("输入数组为null");
        }
        if(arr.length > 0){
            int max = arr[0];
            for (int i = 1,length = arr.length; i < length; i++) {
                if(arr[i] > max){
                    max = arr[i];
                }
            }
            return max;
        }else{
            throw  new RuntimeException("输入数组为空！");
        }
    }

    /**
    *  对数组按照"某个位数"进行排序(桶排序)
    *
    *  参数说明：
    *      a -- 数组
    *      exp -- 指数。对数组a按照该指数进行排序。
    *
    *  例如，对于数组a={50, 3, 542, 745, 2014, 154, 63, 616}；
    *     (01) 当exp=1表示按照"个位"对数组a进行排序
    *     (02) 当exp=10表示按照"十位"对数组a进行排序
    *     (03) 当exp=100表示按照"百位"对数组a进行排序
    *     ...
    */
    private static void countSort(int[] arr, int exp) {
        // 存储"被排序数据"的临时数组
        int[] output = new int[arr.length];
        int[] buckets = new int[10];
        // 将数据出现的次数存储在buckets[]中
        for (int i = 0; i < arr.length; i++){
            buckets[ (arr[i]/exp)%10 ]++;
        }
        // 更改buckets[i]。目的是让更改后的buckets[i]的值，是该数据在output[]中的位置。
        for (int i = 1; i < 10; i++){
            buckets[i] += buckets[i - 1];
        }
        // 将数据存储到临时数组output[]中
        for (int i = arr.length - 1; i >= 0; i--) {
                output[buckets[ (arr[i]/exp)%10 ] - 1] = arr[i];
                buckets[ (arr[i]/exp)%10 ]--;
        }
        // 将排序好的数据赋值给a[]
        for (int i = 0; i < arr.length; i++){
            arr[i] = output[i];
        }

        output = null;
        buckets = null;
    }

    /**
     * 基数排序（带返回值，这不是构造函数，是普通方法）
     * @param arr 数组
     */
    public static void radixSort(int[] arr) {
        // 指数。当对数组按各位进行排序时，exp=1；按十位进行排序时，exp=10；...
        int exp;
        // 数组arr中的最大值
        int max = getMax(arr);

        // 从个位开始，对数组arr按"指数"进行排序
        for (exp = 1; max/exp > 0; exp *= 10){
            countSort(arr, exp);
        }
    }

    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214, 154, 63, 616};
        System.out.println("before sort:");
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d ", arr[i]);
            System.out.printf("\n");
        }

        // 排序
        radixSort(arr);

        System.out.printf("after  sort:");
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d ", arr[i]);
            System.out.printf("\n");
        }
    }
}
