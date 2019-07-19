package top.zaqqmm.oss.feather.core.Action.param;

import java.util.Map;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-19 14:04
 */
public interface Param extends Map<String, Object> {
    /**
     * get String
     * @param param
     * @return
     */
    String getString(String param);

    /**
     * get Integer
     * @param param
     * @return
     */
    Integer getInteger(String param);

    /**
     * get Long
     * @param param
     * @return
     */
    Long getLong(String param);

    /**
     * get Double
     * @param param
     * @return
     */
    Double getDouble(String param);

    /**
     * get Float
     * @param param
     * @return
     */
    Float getFloat(String param);

    /**
     * get Boolean
     * @param param
     * @return
     */
    Boolean getBoolean(String param) ;
}
