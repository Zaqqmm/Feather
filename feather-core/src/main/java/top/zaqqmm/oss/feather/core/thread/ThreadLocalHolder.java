package top.zaqqmm.oss.feather.core.thread;

import io.netty.util.concurrent.FastThreadLocal;
import top.zaqqmm.oss.feather.core.context.FeatherContext;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-16 21:19
 */
public class ThreadLocalHolder {

    private static final FastThreadLocal<Long> LOCAL_TIME = new FastThreadLocal();

    private static final FastThreadLocal<FeatherContext> FEATHER_CONTEXT = new FastThreadLocal();


    public static void setFeatherContext(FeatherContext context) {
        FEATHER_CONTEXT.set(context);
    }

    public static void removeFeatherContext() {
        FEATHER_CONTEXT.remove();
    }

    public static FeatherContext getFeatherContext() {
        return FEATHER_CONTEXT.get();
    }

    public static void setLocalTime(long time) {
        LOCAL_TIME.set(time);
    }

    public static Long getLocalTime() {
        Long time = LOCAL_TIME.get();
        LOCAL_TIME.remove();
        return time;
    }
}
