package snippet.chat.protocol;

import lombok.Data;

import static snippet.chat.protocol.Command.MESSAGE_RESPONSE;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/12
 * @since
 */
@Data
public class MessageResponsePacket extends Packet {
    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
