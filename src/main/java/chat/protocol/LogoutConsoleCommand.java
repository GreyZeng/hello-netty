package chat.protocol;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/21
 * @since
 */
public class LogoutConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutRequestPacket);
    }
}

