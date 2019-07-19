package top.zaqqmm.oss.feather.core.interceptorchain;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-19 13:27
 */
public final class InterceptorChainProvider {
    private static volatile InterceptorChainBuilder builder = null;
    private static final ServiceLoader<InterceptorChainBuilder> LOADER = ServiceLoader.load(InterceptorChainBuilder.class);

    private InterceptorChainProvider(){}

    public static ProcessorInterceptorChain newInterceptorChain() {
        if (builder != null) {
            return builder.build();
        }
        resolveInterceptorChainBuilder();
        if (builder == null) {
            builder = new DefaultInterceptorChainBuilder();
        }
        return builder.build();
    }

    private static void resolveInterceptorChainBuilder() {
        List<InterceptorChainBuilder> list = new ArrayList<InterceptorChainBuilder>();
        boolean hasOther = false;
        for (InterceptorChainBuilder builder : LOADER) {
            if (builder.getClass() != DefaultInterceptorChainBuilder.class) {
                hasOther = true;
                list.add(builder);
            }
        }
        if (hasOther) {
            builder = list.get(0);
        } else {
            // No custom builder, using default.
            builder = new DefaultInterceptorChainBuilder();
        }
    }


}
