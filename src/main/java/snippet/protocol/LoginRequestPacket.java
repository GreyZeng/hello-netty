package snippet.protocol;

import lombok.Data;

import static snippet.protocol.Command.LOGIN_REQUEST;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/15
 * @since
 */
@Data
public class LoginRequestPacket extends Packet {
    private Integer userId;
    private String username;
    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
