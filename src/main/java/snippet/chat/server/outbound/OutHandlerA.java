package snippet.chat.server.outbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/19
 * @since
 */
public class OutHandlerA extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("out-A:" + msg);
        super.write(ctx, msg, promise);
    }
}