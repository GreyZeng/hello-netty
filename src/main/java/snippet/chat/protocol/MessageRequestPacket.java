package snippet.chat.protocol;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/12
 * @since
 */
@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {
    private String message;
    private String toUserId;


    public MessageRequestPacket(String toUserId, String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
