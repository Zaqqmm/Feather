package top.zaqqmm.oss.feather.core.interceptorchain;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-19 13:33
 */
public class DefaultInterceptorChainBuilder implements InterceptorChainBuilder {
    @Override
    public ProcessorInterceptorChain build() {
        ProcessorInterceptorChain processorInterceptorChain = new DefaultProcessorInterceptorChain();
        //todo default interceptors chain
        //processorInterceptorChain.addLast();
        return processorInterceptorChain;
    }
}
