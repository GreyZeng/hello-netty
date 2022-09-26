package chat.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/19
 * @since
 */
public class LifeCycleTestHandler extends ChannelInboundHandlerAdapter {
    // 这个回调方法表示当前Channel的所有逻辑处理已经和某个NIO线程建立了绑定关系，接收新的连接，然后创建一个线程来处理这个连接的读写，只不过在Netty里使用了线程池的方式，
    // 只需要从线程池里去抓一个线程绑定在这个Channel上即可。这里的NIO线程通常指NioEventLoop
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel 绑定到线程（NioEventLoop）：channelRegistered()");
        super.channelRegistered(ctx);
    }

    // 这个回调表明与这个连接对应的NIO线程移除了对这个连接的处理。
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel 取消线程（NioEventLoop）的绑定：channelUnregistered()");
        super.channelUnregistered(ctx);
    }

    // 当Channel的所有业务逻辑链准备完毕（即Channel的Pipeline中已经添加完所有的Handler），
// 以及绑定好一个NIO线程之后，这个连接才真正被激活，接下来就会回调到此方法。
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel 准备就绪：channelActive()");
        super.channelActive(ctx);
    }

    // 这个连接在TCP层面已经不再是ESTABLISH状态了。
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel 被关闭：channelInactive()");
        super.channelInactive(ctx);
    }

    // 客户端向服务端发送数据，每次都会回调此方法，表示有数据可读。
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channel 有数据可读:channelRead()");
        super.channelRead(ctx, msg);
    }

    // 服务端每读完一次完整的数据，都回调该方法，表示数据读取完毕。
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel 某次数据读完：channelReadComplete()");
        super.channelReadComplete(ctx);
    }

    // 表示在当前Channel中，已经成功添加了一个Handler处理器。
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("逻辑处理器被添加：handlerAdded()");
        super.handlerAdded(ctx);
    }

    // 我们给这个连接添加的所有业务逻辑处理器都被移除。
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("逻辑处理器被移除：handlerRemoved()");
        super.handlerRemoved(ctx);
    }
}
