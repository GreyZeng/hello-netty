package snippet.chat.protocol;

import lombok.Data;

import static snippet.chat.protocol.Command.LOGIN_REQUEST; 


@Data
public class LoginRequestPacket extends Packet {
    private String userName;

    private String password;

    @Override
    public Byte getCommand() {

        return LOGIN_REQUEST;
    }
}