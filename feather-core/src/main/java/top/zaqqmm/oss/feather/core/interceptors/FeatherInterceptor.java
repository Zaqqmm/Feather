package top.zaqqmm.oss.feather.core.interceptors;

import top.zaqqmm.oss.feather.core.Action.param.ParamMap;
import top.zaqqmm.oss.feather.core.context.FeatherContext;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-19 13:22
 */
public interface FeatherInterceptor {

    /**
     * Entrance of the interceptor.
     *
     * @param context
     * @param paramMap
     * @param count
     * @param prioritized
     * @param args
     * @throws Throwable
     */
    void entry(FeatherContext context, ParamMap paramMap , int count, boolean prioritized,
               Object... args) throws Throwable;

    /**
     * Means finish of{@link #entry(FeatherContext, ParamMap, int, boolean, Object...)}
     *
     * @param context
     * @param paramMap
     * @param count
     * @param prioritized
     * @param args
     * @throws Throwable
     */
    void fireEntry(FeatherContext context, ParamMap paramMap , int count, boolean prioritized,
                   Object... args) throws Throwable;


    void exit(FeatherContext context, ParamMap paramMap, int count, Object... args);

    void fireExit(FeatherContext context, ParamMap paramMap, int count, Object... args);

}
