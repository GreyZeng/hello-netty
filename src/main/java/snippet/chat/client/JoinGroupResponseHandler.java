package snippet.chat.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import snippet.chat.protocol.JoinGroupResponsePacket;

public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket msg) throws Exception {
        if (msg.isSuccess()) {
            System.out.println("加入群[" + msg.getGroupId() + "] 成功！");
        } else {
            System.out.println("加入群【" + msg.getGroupId() + "] 失败，原因是：" + msg.getReason());
        }
    }
}
