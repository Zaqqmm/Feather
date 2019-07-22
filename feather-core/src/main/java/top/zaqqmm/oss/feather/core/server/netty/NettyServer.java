package top.zaqqmm.oss.feather.core.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.slf4j.Logger;
import top.zaqqmm.oss.feather.core.bean.FeatherBeanManager;
import top.zaqqmm.oss.feather.core.bootstrap.FeatherChannelInitializer;
import top.zaqqmm.oss.feather.core.config.ApplicationConfig;
import top.zaqqmm.oss.feather.core.config.FeatherConfig;
import top.zaqqmm.oss.feather.core.constants.FeatherConstants;
import top.zaqqmm.oss.feather.core.context.FeatherContext;
import top.zaqqmm.oss.feather.core.log.LoggerBuilder;
import top.zaqqmm.oss.feather.core.server.Server;
import top.zaqqmm.oss.feather.core.thread.ThreadLocalHolder;

import static top.zaqqmm.oss.feather.core.config.ConfigurationHolder.getConfiguration;
import static top.zaqqmm.oss.feather.core.constants.FeatherConstants.SystemProperties.APPLICATION_THREAD_SHUTDOWN_NAME;
import static top.zaqqmm.oss.feather.core.constants.FeatherConstants.SystemProperties.APPLICATION_THREAD_WORK_NAME;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-22 16:59
 */
public class NettyServer implements Server {
    private final static Logger LOGGER = LoggerBuilder.getLogger(NettyServer.class);

    private EventLoopGroup boss = new NioEventLoopGroup(1, new DefaultThreadFactory("boss"));
    private EventLoopGroup work = new NioEventLoopGroup(0, new DefaultThreadFactory(APPLICATION_THREAD_WORK_NAME));

    private FeatherConfig appConfig = FeatherConfig.getInstance();
    private Channel channel;

    @Override
    public void startServer() throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(boss, work)
                .channel(NioServerSocketChannel.class)
                .childHandler(new FeatherChannelInitializer());

        ChannelFuture future = bootstrap.bind(FeatherConfig.getInstance().getPort()).sync();
        if (future.isSuccess()) {
            appLog();
        }
        channel = future.channel();
    }

    @Override
    public void joinServer() throws Exception {
        channel.closeFuture().sync();
    }

    private void appLog() {
        Long start = ThreadLocalHolder.getLocalTime();
        ApplicationConfig applicationConfiguration = (ApplicationConfig) getConfiguration(ApplicationConfig.class);
        long end = System.currentTimeMillis();
        LOGGER.info("Cicada started on port: {}.cost {}ms", applicationConfiguration.get(FeatherConstants.FEATHER_PORT), end - start);
        LOGGER.info(">> access http://{}:{}{} <<", "127.0.0.1", appConfig.getPort(), appConfig.getRootPath());
    }

    /**
     * shutdown server
     */
    @Override
    public void shutDownServer() {
        ShutDownThread shutDownThread = new ShutDownThread();
        shutDownThread.setName(APPLICATION_THREAD_SHUTDOWN_NAME);
        Runtime.getRuntime().addShutdownHook(shutDownThread);
    }

    private class ShutDownThread extends Thread {
        @Override
        public void run() {
            LOGGER.info("Feather server stop...");
            FeatherContext.removeContext();

            FeatherBeanManager.getInstance().releaseBean();

            boss.shutdownGracefully();
            work.shutdownGracefully();

            LOGGER.info("Feather server has been successfully stopped.");
        }

    }
}
