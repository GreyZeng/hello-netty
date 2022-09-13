package netty.v2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

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
        bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<>() {
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
                System.out.println("重试次数已经使用完毕");
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
