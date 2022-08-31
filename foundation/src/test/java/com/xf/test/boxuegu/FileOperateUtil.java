package com.xf.test.boxuegu;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: todo(bo_xue_gu文件操作)
 * @Author: xiefu
 * @Date: 2019/9/8 20:40
 */
public class FileOperateUtil {

    public static List<String> list;
    public static String rootPath;
    public static String rootPath2;
    public static String movePath;

    @BeforeAll
    public static void test(){
       list = new ArrayList();
        list.add("7-1 Azkaban介绍以及编译和安装模式");
        list.add("7-1-1 1-Azkaban介绍");
        list.add("7-1-2 2-Azkaban编译和安装模式");
        list.add("7-2 Azkaban的solo模式");
        list.add("7-2-1 3-Azkaban-solo模式-安装");
        list.add("7-2-2 4-Azkaban-solo模式-使用");
        list.add("7-3 Azkaban的Two Server模式");
        list.add("7-3-1 5-Azkaban-two_server模式-安装1");
        list.add("7-3-2 6-Azkaban-two_server模式-安装2");
        list.add("7-3-3 7-Azkaban-two_server模式-安装3和启动运行");
        list.add("7-3-4 8-Azkaban-two_server模式-job任务运行1");
        list.add("7-3-5 9-Azkaban-two_server模式-job任务运行2");
        list.add("7-4 sqoop介绍和安装");
        list.add("7-4-1 10-sqoop入门介绍");
        list.add("7-4-2 11-sqoop安装");
        list.add("7-5 sqoop数据导入和导出");
        list.add("7-5-1 12-sqoop导入-hdfs");
        list.add("7-5-2 13-sqoop导入-hive");
        list.add("7-5-3 14-sqoop增量导入hdfs和导出");
        list.add("7-6 网络流量日志分析");
        list.add("7-6-1 15-网站流量日志分析-分析模型");
        list.add("7-6-2 16-网站流量日志分析-分析指标");
        list.add("7-6-3 17-网站流量日志分析-数据处理流程");
        list.add("7-6-4 18-网站流量日志分析-数据采集");
        list.add("7-6-5 19-网站流量日志分析-数据预处理");
        list.add("7-6-6 20-网站流量日志分析-获取PageView表模型");
        list.add("7-6-7 21-网站流量日志分析-获取Visit表模型");
        list.add("7-6-8 22-网站流量日志分析-hive表数据导入");
        list.add("7-6-9 23-网站流量日志分析-hive表数据分析和导出");
        list.add("7-6-10 24-网站流量日志分析-数据的可视化-Echarts入门");
        list.add("7-6-11 25-网站流量日志分析-数据的可视化-案例分析结果可视化");
       // 打算保存文件的根目录
       rootPath = "H:\\study\\java\\JavaEE在职加薪课\\阶段5：大数据转型必备课程\\5-7 Azkaban.Sqoop";
       movePath = "H:\\study\\java\\JavaEE在职加薪课\\阶段5：大数据转型必备课程\\5-7 Azkaban.Sqoop\\.pcm文件";
       // 之前存放文件按的根目录
       rootPath2 = "C:\\Users\\xiefu\\Documents\\雷电模拟器\\Pictures\\cache";
    }
    /**
     * 生成目录
     */
    @Test
    public void generateDir(){
        List<String> list2 = new ArrayList();
        list2.addAll(list);
        // 需要创建的一级目录
        File dir = null;
        // 需要创建的二级目录
        File secondLevelDirectory;
        for (int i = 0; i < list.size(); i++) {
            // 一级目录前缀
            String dirPrefix = list.get(i).split(" ")[0];
            if(dirPrefix.length() == 3 || dirPrefix.length() == 4){
                String dirPath = rootPath + File.separator + list.get(i);
                dir = new File(dirPath);
                if(!dir.exists()){
                    dir.mkdir();
                    /*for(String s: list2){
                        if(s.startsWith(dirPrefix)){
                            secondLevelDirectory = new File(dirPath, s);
                            secondLevelDirectory.mkdir();
                        }
                    }*/
                }
            }
        }

        modifyFileName();

        moveFile();
    }

    /**
     * 批量修改文件名
     */
    @Test
    public void modifyFileName(){
        // 获取二级目录
        List<String> list2 = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            String filePrefix = list.get(i).split(" ")[0];
            if(filePrefix.length() >= 5){
               list2.add(list.get(i));
            }
        }
        File dir = new File(rootPath2);
        File[] fileArr = dir.listFiles();
        List<File> fileList = new ArrayList();
        // 过滤mp4文件
        for (int j = 0; j < fileArr.length; j++) {
//            files[j].renameTo(new File(rootPath2, list2.get(j)));
            String fileName = fileArr[j].getName();
            if(fileName.substring(fileName.lastIndexOf(".") + 1).equals("mp4")){
                fileList.add(fileArr[j]);
            }
        }
        // 重命名文件
        for (int i = 0; i < fileList.size(); i++) {
            fileList.get(i).renameTo(new File(rootPath2, new StringBuilder(list2.get(i)).append(".mp4").toString()));
        }
        System.out.println("...");
    }

    public void moveFile() {
        File dir = new File(rootPath2);
        File[] fileArr = dir.listFiles();
        for (int j = 0; j < fileArr.length; j++) {
            String fileName = fileArr[j].getName();
            if (fileName.substring(fileName.lastIndexOf(".") + 1).equals("mp4")) {
                fileArr[j].renameTo(new File(rootPath, fileName));
            }
            if (fileName.substring(fileName.lastIndexOf(".") + 1).equals("pcm")) {
                fileArr[j].renameTo(new File(movePath, fileName));
            }
        }

        File[] fileArr2 = dir.listFiles();
        if (fileArr2.length < 1) {
            dir.delete();
        }
    }
}
