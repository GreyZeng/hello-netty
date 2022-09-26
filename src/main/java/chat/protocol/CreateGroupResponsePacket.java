package chat.protocol;

import lombok.Data;

import java.util.List;

import static chat.protocol.Command.CREATE_GROUP_RESPONSE;


/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/21
 * @since
 */
@Data
public class CreateGroupResponsePacket extends Packet {
    private boolean success;

    private String groupId;

    private List<String> userNameList;

    @Override
    public Byte getCommand() {

        return CREATE_GROUP_RESPONSE;
    }
}

