package chat.server.inbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/19
 * @since
 */
public class InHandlerA extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("in-A:" + msg);
        super.channelRead(ctx, msg);
    }
}
