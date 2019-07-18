package top.zaqqmm.oss.feather.core.bean;

import org.slf4j.Logger;
import top.zaqqmm.oss.feather.core.log.LoggerBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-15 10:25
 */
public class FeatherDefaultBeanFactory implements FeatherBeanFactory {

    private static final Logger LOGGER = LoggerBuilder.getLogger(FeatherDefaultBeanFactory.class);
    private static Map<String, Object> beans = new HashMap<>(32);

    @Override
    public void register(Object object) {
        beans.put(object.getClass().getName(), object);
    }

    @Override
    public Object getBean(String name) {
        return beans.get(name);
    }

    @Override
    public void releaseBean() {
        beans = null;
    }
}
