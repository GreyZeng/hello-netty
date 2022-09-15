package protocol;

import lombok.Data;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/15
 * @since
 */
@Data
public abstract class Packet {
    /**
     * 协议版本
     */
    private Byte version = 1;

    /**
     * 指令，由子类实现
     *
     * @return
     */
    public abstract Byte getCommand();
}
