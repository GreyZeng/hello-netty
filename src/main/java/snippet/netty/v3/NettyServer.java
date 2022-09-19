package snippet.netty.v3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Netty 自动绑定递增端口，增加了IO处理逻辑
 *
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/12
 * @since
 */
public class NettyServer {

    public static void main(String[] args) {
        // 引导服务端的启动
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        // 用于监听端口，接收新连接的线程组
        NioEventLoopGroup boss = new NioEventLoopGroup();
        // 表示处理每一个连接的数据读写的线程组
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap.group(boss, worker)
                // 指定IO模型为NIO
                .channel(NioServerSocketChannel.class)
                // 可以给服务端的Channel指定一些属性，非必须
                .attr(AttributeKey.newInstance("serverName"), "nettyServer")
                // 可以给每一个连接都指定自定义属性，非必须
                .childAttr(AttributeKey.newInstance("clientKey"), "clientValue")
                // 使用option方法可以定义服务端的一些TCP参数
                // 这个设置表示系统用于临时存放已经完成三次握手的请求的队列的最大长度，
                // 如果连接建立频繁，服务器创建新的连接比较慢，则可以适当调大这个参数
                .option(ChannelOption.SO_BACKLOG, 1024)
                // 以下两个配置用于设置每个连接的TCP参数
                // SO_KEEPALIVE: 表示是否开启TCP底层心跳机制，true表示开启
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                // TCP_NODELAY：表示是否开启Nagle算法，true表示关闭，false表示开启
                // 如果要求高实时性，有数据发送时就马上发送，就设置为关闭；
                // 如果需要减少发送次数，减少网络交互，就设置为开启。
                .childOption(ChannelOption.TCP_NODELAY, true)
                // 定义后面每一个连接的数据读写
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            // 不管服务端还是客户端，收到数据后都会调用channelRead()方法
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) {
                                ByteBuf byteBuf = (ByteBuf) msg;
                                System.out.println(new Date() + ": 服务端读到数据 -> " + byteBuf.toString(StandardCharsets.UTF_8));
                                // 服务端将读到的数据返回客户端
                                System.out.println(new Date() + ": 服务端写出数据");
                                ctx.channel().writeAndFlush(getByteBuf(ctx));
                            }
                            private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
                                byte[] bytes = "hello world from server!".getBytes(StandardCharsets.UTF_8);
                                ByteBuf buffer = ctx.alloc().buffer();
                                buffer.writeBytes(bytes);
                                return buffer;
                            }
                        });
                    }
                });
        // 本地绑定一个8000端口启动服务
        bind(serverBootstrap, 8000);
    }

    public static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("端口[" + port + "]绑定成功");
            } else {
                System.err.println("端口[" + port + "]绑定失败");
                bind(serverBootstrap, port + 1);
            }
        });
    }
}
