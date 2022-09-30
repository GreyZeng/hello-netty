package source;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 代码阅读
 *
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/12
 * @since
 */
public final class SimpleServer {
    public static void main(String[] args) throws InterruptedException {
        // EventLoopGroup: 服务端的线程模型外观类。这个线程要做的事情
        // 就是不停地检测IO事件，处理IO事件，执行任务。
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 服务端的一个启动辅助类。通过给它设置一系列参数来绑定端口启动服务。
            ServerBootstrap b = new ServerBootstrap();
            b
                    // 设置服务端的线程模型。
                    // bossGroup 负责不断接收新的连接，将新的连接交给 workerGroup 来处理。
                    .group(bossGroup, workerGroup)
                    // 设置服务端的 IO 类型是 NIO。Netty 通过指定 Channel 的类型来指定 IO 类型。
                    .channel(NioServerSocketChannel.class)
                    // 服务端启动过程中，需要经过哪些流程。
                    .handler(new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) {
                            System.out.println("channelActive");
                        }

                        @Override
                        public void channelRegistered(ChannelHandlerContext ctx) {
                            System.out.println("channelRegistered");
                        }

                        @Override
                        public void handlerAdded(ChannelHandlerContext ctx) {
                            System.out.println("handlerAdded");
                        }
                    })
                    // 用于设置一系列 Handler 来处理每个连接的数据
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {

                        }
                    });
            // 绑定端口同步等待。等服务端启动完毕，才会进入下一行代码
            ChannelFuture f = b.bind(8888).sync();
            // 等待服务端关闭端口绑定，这里的作用是让程序不会退出
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
