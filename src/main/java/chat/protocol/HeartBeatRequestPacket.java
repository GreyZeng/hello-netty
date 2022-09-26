package chat.protocol;


import static chat.protocol.Command.HEARTBEAT_REQUEST;

/**
 * 心跳包格式
 *
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/12
 * @since 1.8
 */

public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }
}
