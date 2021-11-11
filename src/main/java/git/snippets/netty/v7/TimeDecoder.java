package git.snippets.netty.v7;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/11/11
 * @since
 */
public class TimeDecoder extends ReplayingDecoder<Void> { // (1)
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) { // (2)
        out.add(in.readBytes(4)); // (4)
    }
}
