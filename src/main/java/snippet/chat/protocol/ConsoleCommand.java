package snippet.chat.protocol;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/21
 * @since
 */
public interface ConsoleCommand {
    void exec(Scanner scanner, Channel channel);
}
