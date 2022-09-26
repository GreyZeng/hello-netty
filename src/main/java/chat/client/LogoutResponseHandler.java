package chat.client;

import chat.SessionUtil;
import chat.protocol.LogoutResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/21
 * @since
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket logoutResponsePacket) {
        SessionUtil.unBindSession(ctx.channel());
    }
}