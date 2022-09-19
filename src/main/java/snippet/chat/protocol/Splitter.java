package snippet.chat.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/12
 * @since
 */
public class Splitter extends LengthFieldBasedFrameDecoder {

    private static final int LEN_FIELD_OFFSET = 7;
    private static final int LEN_FIELD_LENGTH = 4;

    public Splitter() {
        super(Integer.MAX_VALUE, LEN_FIELD_OFFSET, LEN_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if (in.getInt(in.readerIndex()) != PacketCodeC.MAGIC_NUMBER) {
            // 判断前四个字节是否等于自定义的魔数
            ctx.channel().close();
            return null;
        }
        return super.decode(ctx, in);
    }
}
