package top.zaqqmm.oss.feather.core.interceptors.logger;

import top.zaqqmm.oss.feather.core.Action.param.ParamMap;
import top.zaqqmm.oss.feather.core.context.FeatherContext;
import top.zaqqmm.oss.feather.core.interceptorchain.AbstractLinkedInterceptor;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-19 14:34
 */
public class Log extends AbstractLinkedInterceptor {
    @Override
    public void entry(FeatherContext context, ParamMap paramMap, boolean prioritized, Object... args) throws Throwable {
        //todo
    }

    @Override
    public void exit(FeatherContext context, ParamMap paramMap, Object... args) {
        //todo
    }
}
