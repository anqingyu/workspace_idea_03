package com.xf.foundation.io.network.nio;

/**
 * @Description: NIO创建的Server
 * @Author: xiefu
 * @Date: 2019/4/17 14:05
 */
public class Server {

    private static int DEFAULT_PORT = 12345;
    private static ServerHandle serverHandle;

    public static void start(){
        start(DEFAULT_PORT);
    }

    public static synchronized void start(int port){
        if(serverHandle!=null){
            serverHandle.stop();
        }
        serverHandle = new ServerHandle(port);
        new Thread(serverHandle,"Server").start();
    }

    public static void main(String[] args){
        start();
    }
}