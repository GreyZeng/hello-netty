package git.snippets.netty.echo.v9;

import java.util.Date;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/11/11
 * @since
 */
public class UnixTime {

    private final long value;

    public UnixTime() {
        this(System.currentTimeMillis() / 1000L + 2208988800L);
    }

    public UnixTime(long value) {
        this.value = value;
    }

    public long value() {
        return value;
    }

    @Override
    public String toString() {
        return new Date((value() - 2208988800L) * 1000L).toString();
    }
}
