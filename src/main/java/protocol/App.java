package protocol;

import io.netty.buffer.ByteBuf;
import org.junit.Assert;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/15
 * @since
 */
public class App {
    public static void main(String[] args) {
            Serializer serializer = new JSONSerializer();
            LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
            loginRequestPacket.setVersion(((byte) 1));
            loginRequestPacket.setUserId(123);
            loginRequestPacket.setUsername("zhangsan");
            loginRequestPacket.setPassword("password");
            PacketCodeC packetCodeC = new PacketCodeC();
            ByteBuf byteBuf = packetCodeC.encode(loginRequestPacket);
            Packet decodedPacket = packetCodeC.decode(byteBuf);
            Assert.assertArrayEquals(serializer.serialize(loginRequestPacket), serializer.serialize(decodedPacket));
    }
}
