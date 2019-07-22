package top.zaqqmm.oss.feather.core.handle;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultHttpRequest;
import org.slf4j.Logger;
import top.zaqqmm.oss.feather.core.config.FeatherConfig;
import top.zaqqmm.oss.feather.core.log.LoggerBuilder;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-17 12:27
 */
@ChannelHandler.Sharable
public class HttpDispatcher extends SimpleChannelInboundHandler<DefaultHttpRequest> {

    private static final Logger LOGGER = LoggerBuilder.getLogger(HttpDispatcher.class);
    private final FeatherConfig appConfig = FeatherConfig.getInstance();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DefaultHttpRequest msg) throws Exception {
        //todo

    }
}
