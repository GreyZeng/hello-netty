package chat.protocol;

import lombok.Data;

import static chat.protocol.Command.QUIT_GROUP_RESPONSE;

@Data
public class QuitGroupResponsePacket extends Packet {

    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_RESPONSE;
    }
}

