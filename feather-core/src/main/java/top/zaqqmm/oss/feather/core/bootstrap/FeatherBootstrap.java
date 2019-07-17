package top.zaqqmm.oss.feather.core.bootstrap;

import top.zaqqmm.oss.feather.core.FeatherApplication;
import top.zaqqmm.oss.feather.core.bean.FeatherBeanManager;
import top.zaqqmm.oss.feather.core.config.AbstractConfiguration;
import top.zaqqmm.oss.feather.core.config.ApplicationConfig;
import top.zaqqmm.oss.feather.core.config.ConfigurationHolder;
import top.zaqqmm.oss.feather.core.config.FeatherConfig;
import top.zaqqmm.oss.feather.core.constants.FeatherConstants;
import top.zaqqmm.oss.feather.core.reflect.ClassScanner;
import top.zaqqmm.oss.feather.core.thread.ThreadLocalHolder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import static top.zaqqmm.oss.feather.core.constants.FeatherConstants.SystemProperties.APPLICATION_THREAD_MAIN_NAME;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-12 17:01
 */
public class FeatherBootstrap {
    public static void init(Class<?> clazz) throws Exception{
        Thread.currentThread().setName(APPLICATION_THREAD_MAIN_NAME);
        ThreadLocalHolder.setLocalTime(System.currentTimeMillis());
        initConfiguration(clazz);
        initApplicationConfig();

        FeatherBeanManager.getInstance().init();

    }

    private static void initApplicationConfig() throws Exception {
        ApplicationConfig applicationConfiguration = (ApplicationConfig) ConfigurationHolder.getConfiguration(ApplicationConfig.class);

        String rootPath = applicationConfiguration.get(FeatherConstants.ROOT_PATH);
        String port = applicationConfiguration.get(FeatherConstants.FEATHER_PORT);

        if (port == null) {
            throw new Exception("No [cicada.port] exists ");
        }
        FeatherConfig.getInstance().setRootPath(rootPath);
        FeatherConfig.getInstance().setPort(Integer.parseInt(port));
    }

    private static void initConfiguration(Class<?> clazz) throws Exception {
        ThreadLocalHolder.setLocalTime(System.currentTimeMillis());
        FeatherConfig.getInstance().setRootPackageName(clazz);

        List<Class<?>> configuration = ClassScanner.getConfiguration(FeatherConfig.getInstance().getRootPackageName());
        for (Class<?> aClass : configuration) {
            AbstractConfiguration conf = null;
            conf = (AbstractConfiguration) aClass.newInstance();

            InputStream stream;
            String systemProperty = System.getProperty(conf.getPropertiesName());
            if (systemProperty != null) {
                stream = new FileInputStream(new File(systemProperty));
            } else {
                stream = FeatherApplication.class.getClassLoader().getResourceAsStream(conf.getPropertiesName());
            }

            Properties properties = new Properties();
            properties.load(stream);
            conf.setProperties(properties);

            // add configuration cache
            ConfigurationHolder.addConfiguration(aClass.getName(), conf);
        }
    }

}
