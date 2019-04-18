package com.xf.foundation.thread;

public class TestRunnable {
    public static void main(String[] args) {
        for(int i = 0; i < 50 ; i ++){
            System.out.println(Thread.currentThread().getName()+ "    " + i);
            if(i == 30 ){
                // 启动线程为什么用startf方法而不用run方法，这是因为

                //Thread.java 类中的start() 方法通知“线程规划器”此线程已经准备 就绪，等待调用线程对象的run() 方法。这个
                // 过程其实就是让系统安排 一个时间来调用Thread 中的run() 方法，也就是使线程得到运行，启动 线程，具有异步
                // 执行的效果。如果调用代码thread.run() 就不是异步执行 了，而是同步，那么此线程对象并不交给“线程规划器”来
                // 进行处理， 而是由main 主线程来调用run() 方法，也就是必须等run() 方法中的代 码执行完后才可以执行后面的代码。

                Runnable myRunnable = new MyRunnable(); // 创建一个Runnable实现类的对象

                Thread thread1 = new Thread(myRunnable); // 将myRunnable作为Thread target创建新的线程
                Thread thread2 = new Thread(myRunnable);

                thread1.start();                     // 调用start()方法使得线程进入就绪状态
                thread2.start();                     // 调用start()方法使得线程进入就绪状态
            }
        }
    }
}

class MyRunnable implements Runnable{

    public void run() {
            for (int i = 0; i< 10 ; i++){
            System.out.println("线程名：" + Thread.currentThread().getName() + "========第" + i + "次循环");
        }
    }
}
