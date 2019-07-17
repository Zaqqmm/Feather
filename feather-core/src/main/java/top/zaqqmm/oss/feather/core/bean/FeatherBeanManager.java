package top.zaqqmm.oss.feather.core.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-15 10:19
 */
public class FeatherBeanManager {
    private void FeatherBeanManager(){}

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

    public FeatherBeanFactory init() {
        if(featherBeanFactory !=null){
            return  featherBeanFactory;
        }
        resolveFeatherBeanFactory();
        if (featherBeanFactory == null) {
            //todo

            featherBeanFactory = new FeatherDefaultBeanFactory();
        }
        return featherBeanFactory;
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
        return featherBeanFactory.getBean(name) ;
    }

    public void releaseBean(){
        featherBeanFactory.releaseBean();
    }
}
