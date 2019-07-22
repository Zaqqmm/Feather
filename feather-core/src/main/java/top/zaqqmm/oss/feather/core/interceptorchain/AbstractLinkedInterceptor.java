package top.zaqqmm.oss.feather.core.interceptorchain;

import top.zaqqmm.oss.feather.core.Action.param.ParamMap;
import top.zaqqmm.oss.feather.core.context.FeatherContext;
import top.zaqqmm.oss.feather.core.interceptors.FeatherInterceptor;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-19 13:36
 */
public abstract class AbstractLinkedInterceptor implements FeatherInterceptor {
    private AbstractLinkedInterceptor next = null;

    @Override
    public void fireEntry(FeatherContext context, ParamMap paramMap, boolean prioritized, Object... args) throws Throwable {
        if (next != null) {
            next.transformEntry(context, paramMap, prioritized, args);
        }
    }

    @Override
    public void fireExit(FeatherContext context, ParamMap paramMap, Object... args) {
        if (next != null) {
            next.exit(context, paramMap, args);
        }
    }

    void transformEntry(FeatherContext context, ParamMap paramMap, boolean prioritized, Object... args)
            throws Throwable {
        entry(context, paramMap, prioritized, args);
    }

    public AbstractLinkedInterceptor getNext() {
        return next;
    }

    public void setNext(AbstractLinkedInterceptor next) {
        this.next = next;
    }
}
