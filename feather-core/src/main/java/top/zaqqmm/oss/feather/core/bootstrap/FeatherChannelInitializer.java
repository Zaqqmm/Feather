package top.zaqqmm.oss.feather.core.bootstrap;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import top.zaqqmm.oss.feather.core.handle.HttpDispatcher;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-17 13:26
 */
public class FeatherChannelInitializer extends ChannelInitializer<Channel> {
    private final HttpDispatcher httpDispatcher = new HttpDispatcher() ;

    @Override
    public void initChannel(Channel ch) throws Exception {
        ch.pipeline()
                .addLast(new HttpRequestDecoder())
                .addLast(new HttpResponseEncoder())
                .addLast(new ChunkedWriteHandler())
                .addLast(httpDispatcher)
                .addLast("logging", new LoggingHandler(LogLevel.INFO));
    }
}
