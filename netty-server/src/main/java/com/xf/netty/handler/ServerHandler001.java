package com.xf.netty.handler;

import com.xf.netty.pojo.UserParam;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ServerHandler001 extends ChannelHandlerAdapter{

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws  Exception {
       //接受客户端对象
        UserParam user = (UserParam)msg;
        System.out.println("客户端发来的消息 : " + user.getId() + ", " + user.getName() + ", " + user.getRequestMessage());
        //给客户端返回对象
        UserParam response = new UserParam();
        response.setId(user.getId());
        response.setName("response" + user.getId());
        response.setResponseMessage("响应内容" + user.getId());
        ctx.writeAndFlush(response);
        //处理完毕，关闭服务端
        //ctx.addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();// 发生异常，关闭链路
    }
}
