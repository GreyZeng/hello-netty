package chat.server;

import chat.protocol.*;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @author chao.yu
 * chao.yu@dianping.com
 * @date 2018/08/04 06:21.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println(new Date() + ": 客户端开始登录……");
        ByteBuf requestByteBuf = (ByteBuf) msg;
        Packet packet = PacketCodeC.INSTANCE.decode(requestByteBuf);
        ByteBuf byteBuf = null;
        if (packet instanceof LoginRequestPacket loginRequestPacket) {
            // 登录流程
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());
            if (valid(loginRequestPacket)) {
                loginResponsePacket.setSuccess(true);
                System.out.println(new Date() + ": 登录成功!");
            } else {
                loginResponsePacket.setReason("账号密码校验失败");
                loginResponsePacket.setSuccess(false);
                System.out.println(new Date() + ": 登录失败!");
            }
            // 登录响应
            byteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
        } else if (packet instanceof MessageRequestPacket messageRequestPacket) {
            System.out.println(new Date() + ": 收到客户端消息：" + messageRequestPacket.getMessage());
            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
            byteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
        }
        ctx.channel().writeAndFlush(byteBuf);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
