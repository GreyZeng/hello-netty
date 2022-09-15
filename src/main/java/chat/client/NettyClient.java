package chat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Netty 可自动重连的客户端，增加了IO处理逻辑
 *
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/12
 * @since
 */
public class NettyClient {
    static final int MAX_RETRY = 6;
    static final String HOST = "localhost";
    static final int PORT = 8000;

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();

        NioEventLoopGroup group = new NioEventLoopGroup();
        bootstrap
                // 指定线程模型
                .group(group)
                // 指定IO类型为NIO
                .channel(NioSocketChannel.class)
                // attr可以为客户端Channel绑定自定义属性
                .attr(AttributeKey.newInstance("clientName"), "nettyClient")
                // 连接的超时时间，如果超过这个时间，仍未连接到服务端，则表示连接失败
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                // 表示是否开启TCP底层心跳机制，true表示开启
                .option(ChannelOption.SO_KEEPALIVE, true)
                // 是否开启Nagle算法，如果要求高实时性，有数据就马上发送，则为true
                // 如果需要减少发送次数，减少网络交互，就设置为false
                .option(ChannelOption.TCP_NODELAY, true)
                // IO处理逻辑
                .handler(new ChannelInitializer<>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            // 这个方法会在客户端连接建立成功之后被调用
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) {
                                System.out.println(new Date() + ": 客户端写出数据");
                                // 包装成ByteBuf并发送到服务端
                                // 注：Netty中的数据是以 ByteBuf 为单位的。
                                ctx.channel().writeAndFlush(getByteBuf(ctx));
                            }

                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) {
                                ByteBuf byteBuf = (ByteBuf) msg;
                                System.out.println(new Date() + ": 客户端读取到的数据 -> " + byteBuf.toString(StandardCharsets.UTF_8));
                            }

                            private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
                                ByteBuf buffer = ctx.alloc().buffer();
                                byte[] bytes = "hello world".getBytes(StandardCharsets.UTF_8);
                                buffer.writeBytes(bytes);
                                return buffer;
                            }
                        })
                    }
                });
        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }

    private static void connect(final Bootstrap bootstrap, final String host, final int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功！");
            } else if (retry == 0) {
                System.err.println("重试次数已经使用完毕");
            } else {
                // 第几次重试
                int order = (MAX_RETRY - retry) + 1;
                // 本次的重试间隔
                int delay = 1 << order;
                System.out.println(new Date() + "： 连接失败，第" + order + "次重连...");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }
    // TEST

}
