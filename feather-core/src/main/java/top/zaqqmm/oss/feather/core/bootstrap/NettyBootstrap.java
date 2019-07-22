package top.zaqqmm.oss.feather.core.bootstrap;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.slf4j.Logger;
import top.zaqqmm.oss.feather.core.bean.FeatherBeanManager;
import top.zaqqmm.oss.feather.core.config.ApplicationConfig;
import top.zaqqmm.oss.feather.core.config.FeatherConfig;
import top.zaqqmm.oss.feather.core.constants.FeatherConstants;
import top.zaqqmm.oss.feather.core.context.FeatherContext;
import top.zaqqmm.oss.feather.core.log.LoggerBuilder;
import top.zaqqmm.oss.feather.core.server.Server;
import top.zaqqmm.oss.feather.core.server.netty.NettyServer;
import top.zaqqmm.oss.feather.core.thread.ThreadLocalHolder;

import static top.zaqqmm.oss.feather.core.config.ConfigurationHolder.getConfiguration;
import static top.zaqqmm.oss.feather.core.constants.FeatherConstants.SystemProperties.APPLICATION_THREAD_SHUTDOWN_NAME;
import static top.zaqqmm.oss.feather.core.constants.FeatherConstants.SystemProperties.APPLICATION_THREAD_WORK_NAME;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-12 17:01
 */
public class NettyBootstrap {
    private final static Logger LOGGER = LoggerBuilder.getLogger(NettyBootstrap.class);

    private FeatherConfig appConfig = FeatherConfig.getInstance();
    private EventLoopGroup boss = new NioEventLoopGroup(1, new DefaultThreadFactory("boss"));
    private EventLoopGroup work = new NioEventLoopGroup(0, new DefaultThreadFactory(APPLICATION_THREAD_WORK_NAME));
    private Channel channel;

    private Server server = new NettyServer();


    public void start() throws Exception {
        // start
        server.startServer();

        // register shutdown hook
        server.shutDownServer();

        // synchronized channel
        server.joinServer();
    }
}
