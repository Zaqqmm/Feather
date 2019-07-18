package top.zaqqmm.oss.feather.core.bean.reader;

import org.slf4j.Logger;
import top.zaqqmm.oss.feather.core.log.LoggerBuilder;

import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.Enumeration;
import java.util.Set;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-18 17:22
 */
public class DefaultClassReader  implements ClassReader {

    private static final Logger LOGGER = LoggerBuilder.getLogger(DefaultClassReader.class);
    private static volatile DefaultClassReader classReader;

    //private Map<String, Set<Class<?>>> packageClassMap = new HashMap<>();

    private DefaultClassReader() {
    }

    public static DefaultClassReader getInstance() {
        if (classReader == null) {
            synchronized (DefaultClassReader.class) {
                if (classReader == null) {
                    classReader = new DefaultClassReader();
                }
            }
        }
        return classReader;
    }

    @Override
    public Set<Class<?>> readClasses(Scanner scanner) {
        //todo
        Set<Class<?>> classes = null;
        String packageName = scanner.getPackageName();
        Class<? extends Annotation> annotation = scanner.getAnnotation();
        Class<?> parent = scanner.getParent();
        boolean recursive = scanner.isRecursive();
        if (packageName != null && !"".equals(packageName)) {
            classes = readClassesByPackage(packageName);
        }
        return null;
    }

    private Set<Class<?>> baseClassesScan(String packageName) {
        boolean recursive = true;
        String packageDirName = packageName.replace('.', '/');
        Enumeration<URL> dirs;
        return null;
    }

    private Set<Class<?>>findClassByPackage(){
        return null;
    }
}
