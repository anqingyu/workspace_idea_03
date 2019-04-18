package com.anqy.xyz.jvm.chapter02;

/**
 * @Description: todo(测试JVM StackOverFlowError)
 *      VM Args:-Xss128k
 * @Author: xiefu
 * @Date: 2019/3/29 21:02
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength ++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Exception e) {
            System.out.println("stack length:" + oom.stackLength);
            e.printStackTrace();
        }
    }
}
