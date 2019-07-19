package top.zaqqmm.oss.feather.core.interceptorchain;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-19 14:22
 */
public abstract class ProcessorInterceptorChain extends AbstractLinkedInterceptor {

    public abstract void addFirst(AbstractLinkedInterceptor protocolProcessor);

    public abstract void addLast(AbstractLinkedInterceptor protocolProcessor);

}
