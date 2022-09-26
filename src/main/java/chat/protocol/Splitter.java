package chat.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 解析报文格式
 * <p>
 * |魔数 4bit|版本标识 1bit|序列号算法 1bit|指令 1bit|数据长度 4bit|数据内容 若干字节|
 * <a href="https://www.processon.com/view/link/6321bf1be401fd4346294be5">报文格式</a>
 *
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/12
 * @since 1.8
 */
public class Splitter extends LengthFieldBasedFrameDecoder {
    /**
     * |魔数 4bit|版本标识 1bit|序列号算法 1bit|指令 1bit|
     */
    private static final int LENGTH_FIELD_OFFSET = 7;
    /**
     * |数据长度 4bit|
     */
    private static final int LENGTH_FIELD_LENGTH = 4;

    public Splitter() {
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if (in.getInt(in.readerIndex()) != PacketCodec.MAGIC_NUMBER) {
            ctx.channel().close();
            return null;
        }
        return super.decode(ctx, in);
    }
}
