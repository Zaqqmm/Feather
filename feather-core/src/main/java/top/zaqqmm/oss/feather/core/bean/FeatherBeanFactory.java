package top.zaqqmm.oss.feather.core.bean;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-15 19:24
 */
public interface FeatherBeanFactory {

    void register(Object object);

    Object getBean(String name) throws Exception;

    void releaseBean() ;
}
