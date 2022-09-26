package chat.protocol;

import lombok.Data;

import static chat.protocol.Command.LOGOUT_RESPONSE;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/21
 * @since
 */
@Data
public class LogoutResponsePacket extends Packet {

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {

        return LOGOUT_RESPONSE;
    }
}
