package top.zaqqmm.oss.feather.core;

import top.zaqqmm.oss.feather.core.bootstrap.FeatherBootstrap;
import top.zaqqmm.oss.feather.core.bootstrap.NettyBootstrap;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-16 21:49
 */
public class FeatherApplication {
    //private Set<Class<?>> primarySources;

    public static void run(Class<?> primarySource,String... args){
        try {
            new FeatherApplication(primarySource).run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private FeatherApplication(Class<?> primarySource) throws Exception {
        FeatherBootstrap.init(primarySource);
    }
    private void run(String... args) throws Exception{
        NettyBootstrap.start();
    }

}
