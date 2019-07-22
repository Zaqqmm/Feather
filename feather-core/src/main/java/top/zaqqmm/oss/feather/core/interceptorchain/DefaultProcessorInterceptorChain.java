package top.zaqqmm.oss.feather.core.interceptorchain;

import top.zaqqmm.oss.feather.core.Action.param.ParamMap;
import top.zaqqmm.oss.feather.core.context.FeatherContext;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-19 14:24
 */
public class DefaultProcessorInterceptorChain extends ProcessorInterceptorChain {
    AbstractLinkedInterceptor first = new AbstractLinkedInterceptor() {

        @Override
        public void entry(FeatherContext context, ParamMap paramMap, boolean prioritized, Object... args)
                throws Throwable {
            super.fireEntry(context, paramMap, prioritized, args);
        }

        @Override
        public void exit(FeatherContext context, ParamMap paramMap, Object... args) {
            super.fireExit(context, paramMap, args);
        }

    };
    AbstractLinkedInterceptor end = first;
    @Override
    public void addFirst(AbstractLinkedInterceptor protocolProcessor) {

    }

    @Override
    public void addLast(AbstractLinkedInterceptor protocolProcessor) {

    }

    @Override
    public void entry(FeatherContext context, ParamMap paramMap, boolean prioritized, Object... args) throws Throwable {

    }

    @Override
    public void exit(FeatherContext context, ParamMap paramMap, Object... args) {

    }

    @Override
    public void setNext(AbstractLinkedInterceptor next) {
        addLast(next);
    }

    @Override
    public AbstractLinkedInterceptor getNext() {
        return first.getNext();
    }
}
