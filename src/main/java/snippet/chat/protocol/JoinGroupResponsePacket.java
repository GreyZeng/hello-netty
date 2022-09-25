package snippet.chat.protocol;

import lombok.Data;
import snippet.chat.protocol.Packet;

import static snippet.chat.protocol.Command.JOIN_GROUP_RESPONSE;

@Data
public class JoinGroupResponsePacket extends Packet {
    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {

        return JOIN_GROUP_RESPONSE;
    }
}
