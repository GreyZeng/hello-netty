package chat.server;

import chat.protocol.Packet;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

import static chat.protocol.Command.CREATE_GROUP_REQUEST;
import static chat.protocol.Command.GROUP_MESSAGE_REQUEST;
import static chat.protocol.Command.JOIN_GROUP_REQUEST;
import static chat.protocol.Command.LIST_GROUP_MEMBERS_REQUEST;
import static chat.protocol.Command.LOGOUT_REQUEST;
import static chat.protocol.Command.MESSAGE_REQUEST;
import static chat.protocol.Command.QUIT_GROUP_REQUEST;

/**
 * Server 端根据不同的 Command 调用不同的 Handler 去处理请求
 *
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/21
 * @since 1.8
 */
@ChannelHandler.Sharable
public class IMHandler extends SimpleChannelInboundHandler<Packet> {
    public static final IMHandler INSTANCE = new IMHandler();

    private Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    private IMHandler() {
        handlerMap = new HashMap<>();
        handlerMap.put(MESSAGE_REQUEST, MessageRequestHandler.INSTANCE);
        handlerMap.put(CREATE_GROUP_REQUEST, CreateGroupRequestHandler.INSTANCE);
        handlerMap.put(JOIN_GROUP_REQUEST, JoinGroupRequestHandler.INSTANCE);
        handlerMap.put(QUIT_GROUP_REQUEST, QuitGroupRequestHandler.INSTANCE);
        handlerMap.put(LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestHandler.INSTANCE);
        handlerMap.put(GROUP_MESSAGE_REQUEST, GroupMessageRequestHandler.INSTANCE);
        handlerMap.put(LOGOUT_REQUEST, LogoutRequestHandler.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        handlerMap.get(packet.getCommand()).channelRead(ctx, packet);
    }
}
