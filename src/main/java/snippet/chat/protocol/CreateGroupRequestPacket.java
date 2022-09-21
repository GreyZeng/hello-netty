package snippet.chat.protocol;

import lombok.Data;

import java.util.List;

import static snippet.chat.protocol.Command.CREATE_GROUP_REQUEST;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/21
 * @since
 */
@Data
public class CreateGroupRequestPacket extends Packet {
    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_REQUEST;
    }
}
