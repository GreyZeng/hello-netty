package chat.protocol;

import lombok.Data;

import static chat.protocol.Command.LOGIN_RESPONSE;

@Data
public class LoginResponsePacket extends Packet {
    private String userId;
    private String userName;
    private boolean success;
    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}

