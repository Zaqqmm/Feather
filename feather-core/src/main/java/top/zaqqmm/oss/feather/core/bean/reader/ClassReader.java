package top.zaqqmm.oss.feather.core.bean.reader;

import java.util.Set;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-18 17:07
 */
public interface ClassReader {
    Set<Class<?>> readClasses(Scanner scanner);
}
