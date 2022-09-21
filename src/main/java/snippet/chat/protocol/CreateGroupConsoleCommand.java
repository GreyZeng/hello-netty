package snippet.chat.protocol;
import io.netty.channel.Channel;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/21
 * @since
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {
    private static final String USER_ID_SPLITTER = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();
        System.out.print("【拉人群聊】输入userId列表，userId之间用英文逗号隔开：");
        String userIds = scanner.next();
        createGroupRequestPacket.setUserIdList(Arrays.asList(userIds.split(USER_ID_SPLITTER)));
        channel.writeAndFlush(createGroupRequestPacket);
    }
}
