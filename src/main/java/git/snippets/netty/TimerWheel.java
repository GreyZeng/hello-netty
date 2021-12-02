package git.snippets.netty;

import io.netty.util.HashedWheelTimer;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Netty时间轮
 *
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/12/2
 * @since 1.8
 */
public class TimerWheel {
    public static void main(String[] args) throws InterruptedException {
        HashedWheelTimer wheelTimer = new HashedWheelTimer(Executors.defaultThreadFactory(), 1, TimeUnit.SECONDS, 64, false);
        System.out.println("time wheel start@" + LocalDateTime.now());
        wheelTimer.newTimeout(timeout -> System.out.println("timeout 10s --> " + LocalDateTime.now()), 10, TimeUnit.SECONDS);
        wheelTimer.newTimeout(timeout -> System.out.println("timeout 20s --> " + LocalDateTime.now()), 20, TimeUnit.SECONDS);
        Thread.currentThread().join();
    }
}
