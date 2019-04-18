package com.xf.foundation.io.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @Description: todo(文件Utils)
 * @Author: xiefu
 * @Date: 2019/4/16 14:34
 */
public class FileUtils {
    public static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 读取文件（字节流）
     *   InputStream类中的三种read方法:
     *      read():返回值为0到255的int类型的值，返回值为字符的ACSII值(如a就返回97,n就返回110);
     *          如果没有可用的字节,因为已经到达流的末尾, -1返回的值
     *      read(byte[] b):返回值为实际读取的字节数;
     *          如果没有可用的字节,因为已经到达流的末尾, -1返回的值
     *      read( byte [] b , int off , int len):试图读取多达 len字节,但可能读取到少于len字节。返回实际读取的字节数为整数;
     *          如果没有可用的字节,因为已经到达流的末尾, -1返回的值
     */
    public static void operateByBytes(String readFileName, String writeFileName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(readFileName);
        FileOutputStream fileOutputStream = new FileOutputStream(writeFileName);

        //读取数据
        //一次性取多少字节
        byte[] bytes = new byte[2048];
        int n = -1;
        while((n = fileInputStream.read(bytes, 0, bytes.length)) != -1){
            //转换成字符串，这里可以实现字节到字符串的转换，比较实用
            String str = new String(bytes, 0, n,"GBK");
            System.out.println(str);
            // 写入相关文件
            fileOutputStream.write(bytes, 0, n);
        }

        // 关流
        fileInputStream.close();
        fileOutputStream.close();

    }

    /**
     * 读取文件(缓存字节流)
     */
    public static void operateByBytesBuffered(String readFileName, String writeFileName) throws IOException{
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(readFileName));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(writeFileName));

        byte[] bytes = new byte[2048];
        int n = -1;

        while((n = bis.read(bytes, 0, bytes.length)) != -1){
            String str = new String(bytes, 0, n, "GBK");
            System.out.println(str);

            bos.write(bytes, 0, n);
        }

        // 清除缓存
        bos.flush();

        // 关闭流
        bis.close();
        bos.close();
    }

    /**
     * 读取文件(字节流)
     *    InputStreamReader、OutputStreamWriter（字节流，这种方式不建议使用，不能直接字节长度读写）。使用范围：用做字符转换
     * @param readFileName 读文件
     * @param writeFileName 写文件
     */
    public static void  operateByCharacter(String readFileName, String writeFileName) throws IOException{
        InputStreamReader in = new InputStreamReader(new FileInputStream(readFileName), "GBK");
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(writeFileName), "GBK");

        int len = -1;
        while ((len = in.read()) != -1){
            System.out.println(len);

            out.write(len);
        }

        // 清除缓存
        out.flush();
        // 关闭流
        in.close();
        out.close();
    }

    /**
     * 读取文件(字符缓存流，BufferedReader提供readLine方法读取一行文本)
     * @param readFileName 读文件
     * @param writeFileName 写文件
     */
    public static void  operateByCharacterBuffered(String readFileName, String writeFileName) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(readFileName), "utf-8")); //这里主要是涉及中文
//        BufferedReader bufferedReader = new BufferedReader(new FileReader(readFileName));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(writeFileName),"utf-8"));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(writeFileName));

        String str = null;
        while ((str = bufferedReader.readLine()) != null) {
            System.out.println(str);
            //写入相关文件
            bufferedWriter.write(str);
            bufferedWriter.newLine();
        }

        // 清除缓存
        bufferedWriter.flush();
        // 关闭流
        bufferedReader.close();
        bufferedWriter.close();
    }

    /**
     * 使用Reader、PrintWriter读取文件(字节流)
     */
    public static void operateByPrintWriter(String readFileName, String writeFileName) throws IOException{
        //读取文件(字节流)
        Reader in = new InputStreamReader(new FileInputStream(readFileName),"GBK");
        //写入相应的文件
//        PrintWriter out = new PrintWriter(new FileWriter(writeFileName));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(writeFileName),"utf-8"));

        byte[] bytes = new byte[1024];
        int len = -1;
        while ((len = in.read()) != -1) {
            System.out.println(len);
            //写入相关文件
            out.write(len);
        }
        // 清除缓存
        out.flush();
        // 关闭流
        in.close();
        out.close();
    }

    public static void main(String[] args){
        try {
            operateByBytes("I:\\test\\test001.txt", "I:\\test\\test002.txt");
//            operateByBytesBuffered("I:\\test\\test001.txt", "I:\\test\\test002.txt");
//            operateByCharacter("I:\\test\\test001.txt", "I:\\test\\test002.txt");
//            operateByCharacterBuffered("I:\\test\\test003.txt", "I:\\test\\test004.txt");
//            operateByPrintWriter("I:\\test\\test001.txt", "I:\\test\\test002.txt");
//            print(new File("I:" + File.separator + "test"));
        } catch (Exception e) {
//            e.printStackTrace();
            LOGGER.error("Exception is:", e.getMessage());
        }
    }

    /**
     * 列出指定目录的全部内容
     * @param file
     */
    public static void print(File file){    // 递归调用
        // 判断对象是否为空
        if(file!=null){
            // 如果是目录
            if(file.isDirectory()){
                // 列出全部的文件
                File f[] = file.listFiles() ;
                // 判断此目录能否列出
                if(f!=null){
                    for(int i=0;i<f.length;i++){
                        // 因为给的路径有可能是目录，所以，继续判断，继续递归。
                        print(f[i]) ;
                    }
                }
            }else{
                // 输出路径
                System.out.println(file) ;
            }
        }
    }

}
