package chat.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.nio.ByteOrder;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/12
 * @since
 */
public class Spliter extends LengthFieldBasedFrameDecoder {

    private static final int LEN_FIELD_OFFSET = 7;
    private static final int LEN_FIELD_LENGTH = 4;

    public Spliter() {
        super(Integer.MAX_VALUE, LEN_FIELD_OFFSET, LEN_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if (in.getInt(in.readerIndex()) != PacketCodeC.MAGIC_NUMBER) {
            ctx.channel().close();
            return null;
        }
        return super.decode(ctx, in);
    }
}
