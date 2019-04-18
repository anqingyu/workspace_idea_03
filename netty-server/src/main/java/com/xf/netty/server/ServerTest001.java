package com.xf.netty.server;

import com.xf.netty.handler.ServerHandler001;
import com.xf.netty.utils.MarshallingCodeCFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.marshalling.CompatibleMarshallingDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.MarshallingConfiguration;

/**
 * @author xiefu
 */
public class ServerTest001 {
    public void bind(int port) throws InterruptedException {
        // 线程组：用来接收进来的连接
        EventLoopGroup bossGroop = new NioEventLoopGroup();
        // 线程组：用来处理已经被接收的连接
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            // Bootstrap用来配置参数
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroop, workerGroup)
                    // 注册服务端的channel
                    .channel(NioServerSocketChannel.class)
                    /**
                     * BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，
                     * 用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，将使用默认值50。
                     * 服务端处理客户端连接请求是顺序处理的，所以同一时间只能处理一个客户端连接，多个客户端来的时候，
                     * 服务端将不能处理的客户端连接请求放在队列中等待处理，backlog参数指定了队列的大
                     */
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    // 设置日志
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //通过工厂类创建MarshallingEncoder解码器，并添加到ChannelPipeline.
                            socketChannel.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                            //通过工厂类创建MarshallingEncoder编码器，并添加到ChannelPipeline中。
                            socketChannel.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                            //5s没有交互，就会关闭channel
                            socketChannel.pipeline().addLast(new ReadTimeoutHandler(5));
                            // 服务端业务处理类
                            socketChannel.pipeline().addLast(new ServerHandler001());
                        }
                    });

            //绑定端口
            ChannelFuture future = b.bind(port).sync();
            //等待关闭(程序阻塞在这里等待客户端请求)
            future.channel().closeFuture().sync();
        }finally {
            // 关闭线程，释放线程资源
            bossGroop.shutdownGracefully();
            // 关闭线程，释放线程资源
            workerGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception{
        int port = 8765;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                // 采用默认值
            }
        }
        new ServerTest001().bind(port);
    }
}
