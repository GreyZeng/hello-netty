package snippet.netty.v1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;

/**
 * Netty 实现客户端
 *
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/12
 * @since
 */
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        bootstrap
                // 指定线程模型
                .group(group)
                // 指定 IO 类型为 NIO
                .channel(NioSocketChannel.class)
                // IO 处理逻辑
                .handler(new ChannelInitializer<>() {
            @Override
            protected void initChannel(Channel channel) {
                channel.pipeline().addLast(new StringEncoder());
            }
        });
        Channel channel = bootstrap.connect("localhost", 8000).channel();
        while (true) {
            channel.writeAndFlush(new Date() + ": hello world!");
            Thread.sleep(2000);
        }
    }
}
