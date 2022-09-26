package snippet.chat.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import snippet.chat.protocol.HeartBeatRequestPacket;

import java.util.concurrent.TimeUnit;

/**
 * 发送心跳的 Handler
 *
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/12
 * @since
 */
public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {
    private static final int HEARTBEAT_INTERVAL = 5;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        scheduleSendHeartBeat(ctx);
        super.channelActive(ctx);
    }

    // 定时发送心跳
    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
        ctx.executor().schedule(() -> {
            // System.out.println("客户端发送心跳......");
            ctx.writeAndFlush(new HeartBeatRequestPacket());
            scheduleSendHeartBeat(ctx);
        }, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }
}
