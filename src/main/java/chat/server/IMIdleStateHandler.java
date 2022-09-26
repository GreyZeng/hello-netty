package chat.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * 心跳检测处理 Handler
 *
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/21
 * @since 1.8
 */
public class IMIdleStateHandler extends IdleStateHandler {
    private static final int READER_IDLE_TIME = 15;

    public IMIdleStateHandler() {
        // 第一个参数：读空闲时间，如果这段时间如果没有读到数据，就表示连接假死。
        // 第二个参数：写空闲时间，如果这个段时间没有写数据，就表示连接假死
        // 第三个参数：读写空闲时间，如果这段时间没有产生数据读或写，就标识连接假死
        // 写空闲和读写空闲都是0，读空闲是15秒，表示如果15秒内没有读到数据，就表示连接假死
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    // 连接假死后会调用这个方法
    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        System.out.println(READER_IDLE_TIME + "秒内未读取到数据，关闭连接");
        ctx.channel().close();
    }
}
