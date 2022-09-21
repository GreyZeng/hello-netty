package snippet.chat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.DefaultChannelGroup;
import snippet.chat.IDUtil;
import snippet.chat.SessionUtil;
import snippet.chat.protocol.CreateGroupRequestPacket;
import snippet.chat.protocol.CreateGroupResponsePacket;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/21
 * @since
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {
        List<String> userIdList = msg.getUserIdList();
        List<String> userNameList = new ArrayList<>();
        // 创建一个 channel 分组
        DefaultChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
        for (String userId : userIdList) {
            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null) {
                channelGroup.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUserName());
            }
        }
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setGroupId(IDUtil.randomId());
        createGroupResponsePacket.setUserNameList(userNameList);

        channelGroup.writeAndFlush(createGroupResponsePacket);

        System.out.print("群创建成功：id为[" + createGroupResponsePacket.getGroupId() + "],");
        System.out.println("群里有：" + createGroupResponsePacket.getUserNameList());
    }
}
