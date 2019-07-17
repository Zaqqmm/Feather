package top.zaqqmm.oss.feather.core.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-17 10:05
 */
public class LoggerBuilder {


    /**
     * get static Logger
     *
     * @param clazz
     * @return
     */
    public static Logger getLogger(Class clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}
