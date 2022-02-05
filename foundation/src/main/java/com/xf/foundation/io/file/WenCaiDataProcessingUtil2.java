package com.xf.foundation.io.file;

import com.xf.foundation.poi_ooxml.ExcelUtil;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 问财数据整理工具类
 * @Author: xiefu
 * @Date: 2021/2/7 5:53
 */
public class WenCaiDataProcessingUtil2 {
    @Test
    public void test1() throws Exception {
        // 使用ArrayList来存储每行读取到的字符串
        ArrayList<String> arrayList;
        ArrayList<String> arrayList1 = new ArrayList<>();
        ArrayList<String> arrayList2 = new ArrayList<>();
        try {
            arrayList = arrayList1;
            FileReader fr = new FileReader("H:\\新建文件夹 (2)/a.txt");
            BufferedReader bf = new BufferedReader(fr);
            String str;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                if(!str.equals("")){
                    arrayList.add(str);
                }
            }
            bf.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 输出股票代码/行业代码
        System.out.println("股票代码/行业代码:====================");
        for(int i = 0; i < arrayList1.size();){
            String s = arrayList1.get(i+1);
            i = i + 8;
            System.out.println(s);
        }

        // 输出输出股票名称/行业名称
        System.out.println("股票名称/行业名称:====================");
        for(int i = 0; i < arrayList1.size();){
            String s = arrayList1.get(i+2);
            i = i + 8;
            System.out.println(s);
        }

        // 输出区间涨跌幅
        System.out.println("区间涨跌幅:====================");
        for(int i = 0; i < arrayList1.size();){
            String s = arrayList1.get(i+5);
            i = i + 8;
            System.out.println(s);
        }

        // 输出现价
        System.out.println("行业:====================");
        for(int i = 0; i < arrayList1.size();){
            String s = arrayList1.get(i+3);
            i = i + 8;
            System.out.println(s);
        }
    }
}