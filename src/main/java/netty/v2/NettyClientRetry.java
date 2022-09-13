package netty.v2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Netty 实现可自动重连的客户端
 *
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/12
 * @since
 */
public class NettyClientRetry {
    static final int MAX_RETRY = 6;

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
                    protected void initChannel(Channel channel) {

                    }
                });
        connect(bootstrap, "localhost", 8000, MAX_RETRY);
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

}
