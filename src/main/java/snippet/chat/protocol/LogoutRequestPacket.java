package snippet.chat.protocol;

import lombok.Data;

import static snippet.chat.protocol.Command.LOGOUT_REQUEST;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/21
 * @since
 */
@Data
public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {

        return LOGOUT_REQUEST;
    }
}