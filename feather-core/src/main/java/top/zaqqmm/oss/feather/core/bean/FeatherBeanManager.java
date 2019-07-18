package top.zaqqmm.oss.feather.core.bean;

import top.zaqqmm.oss.feather.core.bean.annotation.Component;
import top.zaqqmm.oss.feather.core.bean.reader.ClassReader;
import top.zaqqmm.oss.feather.core.bean.reader.DefaultClassReader;
import top.zaqqmm.oss.feather.core.bean.reader.Scanner;
import top.zaqqmm.oss.feather.core.config.FeatherConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-15 19:19
 */
public class FeatherBeanManager {
    private void FeatherBeanManager() {
    }

    public static volatile FeatherBeanManager featherBeanManager;
    private static FeatherBeanFactory featherBeanFactory;
    private static final ServiceLoader<FeatherBeanFactory> LOADER = ServiceLoader.load(FeatherBeanFactory.class);


    public static FeatherBeanManager getInstance() {
        if (featherBeanManager == null) {
            synchronized (FeatherBeanManager.class) {
                if (featherBeanManager == null) {
                    featherBeanManager = new FeatherBeanManager();
                }
            }
        }
        return featherBeanManager;
    }

    public void init() throws IllegalAccessException, InstantiationException {
        resolveFeatherBeanFactory();
        if (featherBeanFactory == null) {
            featherBeanFactory = new FeatherDefaultBeanFactory();
        }
        Scanner.Builder builder = new Scanner.Builder();
        Scanner scanner = builder.annotation(Component.class)
                .packageName(FeatherConfig.getInstance().getRootPackageName())
                .build();
        Set<Class<?>> classes = DefaultClassReader.getInstance().readClasses(scanner);
        for (Class<?> clazz : classes) {
            Object instance;
            instance = clazz.newInstance();
            featherBeanFactory.register(instance) ;
        }

    }

    private void resolveFeatherBeanFactory() {
        List<FeatherBeanFactory> list = new ArrayList<FeatherBeanFactory>();
        boolean hasOther = false;
        for (FeatherBeanFactory factory : LOADER) {
            if (factory.getClass() != FeatherDefaultBeanFactory.class) {
                hasOther = true;
                list.add(factory);
            }
        }
        if (hasOther) {
            featherBeanFactory = list.get(0);
        } else {
            // No custom builder, using default.
            featherBeanFactory = new FeatherDefaultBeanFactory();
        }

    }

    public Object getBean(String name) throws Exception {
        return featherBeanFactory.getBean(name);
    }

    public void releaseBean() {
        featherBeanFactory.releaseBean();
    }
}
