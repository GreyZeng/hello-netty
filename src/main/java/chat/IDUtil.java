package chat;

import java.util.UUID;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/21
 * @since
 */
public class IDUtil {
    public static String randomId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
