package top.zaqqmm.oss.feather.core.bean;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-18 9:51
 */
public class FeatherBeanManagerTest {

    @Test
    public void getInstance() {
        Class<? extends FeatherBeanManager> aClass = FeatherBeanManager.getInstance().getClass();
        Assert.assertNotNull("Do not have FeatherBeanManager.", aClass);
    }

    @Test
    public void init() {
        FeatherBeanFactory featherbeanFactory = FeatherBeanManager.getInstance().init();
        Assert.assertEquals("didn't load the default bean factory.", FeatherDefaultBeanFactory.class,featherbeanFactory.getClass());
    }

    @Test
    public void getBean() {
    }

    @Test
    public void releaseBean() {
    }
}