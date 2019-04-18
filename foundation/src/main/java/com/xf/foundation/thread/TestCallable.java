package com.xf.foundation.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 使用Callable和Future接口创建线程。具体是创建Callable接口的实现类，并实现clall()方法。并使用FutureTask类来
 * 包装Callable实现类的对象，且以此FutureTask对象作为Thread对象的target来创建线程
 */
public class TestCallable {
    public static void main(String[] args) {
        MyCallable myCallable = new MyCallable();
        //使用FutureTask来包装MyCallable对象
        FutureTask<Integer> futureTask = new FutureTask<Integer>(myCallable);
        for(int i = 0; i < 100; i ++){
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 30) {
                //FutureTask对象作为Thread对象的target创建新的线程
                Thread thread = new Thread(futureTask);
                //线程进入到就绪状态
                thread.start();
            }
        }

        System.out.println("主线程for循环执行完毕..");

        try {
            //取得新创建的新线程中的call()方法返回的结果
            int sum = futureTask.get();
            System.out.println("sum = " + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

class MyCallable implements Callable{
    // 与run()方法不同的是，call()方法具有返回值
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 100; i ++){
            System.out.println(Thread.currentThread().getName() + "   " + i);
            sum += i;
        }
        return sum;
    }
}