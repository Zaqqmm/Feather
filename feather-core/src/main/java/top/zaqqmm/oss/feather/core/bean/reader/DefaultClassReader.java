package top.zaqqmm.oss.feather.core.bean.reader;

import org.slf4j.Logger;
import top.zaqqmm.oss.feather.core.log.LoggerBuilder;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * @description: ${DESCRIPTION}
 * @create 2019-07-18 17:22
 */
public class DefaultClassReader implements ClassReader {

    private static final Logger LOGGER = LoggerBuilder.getLogger(DefaultClassReader.class);
    private static volatile DefaultClassReader classReader;

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
        //todo no test has been done yet.
        Set<Class<?>> classes = null;
        String packageName = scanner.getPackageName();
        Class<? extends Annotation> annotation = scanner.getAnnotation();
        Class<?> parent = scanner.getParent();
        boolean recursive = scanner.isRecursive();
        if (classes == null) {
            classes = new HashSet<>(32);
            String packageDirName = packageName.replace('.', '/');
            // Defines an enumerated collection and loops to process the URL in this directory
            Enumeration<URL> dirs;
            try {
                dirs = this.getClass().getClassLoader().getResources(packageDirName);
                // Loop iterations down
                while (dirs.hasMoreElements()) {
                    URL url = dirs.nextElement();
                    String filePath = new URI(url.getFile()).getPath();
                    findAndAddClasssByPackage(packageName, filePath, recursive, classes);
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (null != annotation) {
            findClassesByAnnotation(annotation, classes);
        }
        if (null != parent) {
            findClassesByParent(parent, classes);
        }

        return classes;
    }

    private void findClassesByParent(Class<?> parent, Set<Class<?>> classes) {
        for (Class<?> clazz : classes) {
            if (null == clazz.getSuperclass() || !clazz.getSuperclass().equals(parent)) {
                classes.remove(clazz);
            }
        }
    }

    private void findClassesByAnnotation(Class<? extends Annotation> annotation, Set<Class<?>> classes) {
        for (Class<?> clazz : classes) {
            if (null == clazz.getAnnotation(annotation)) {
                classes.remove(clazz);
            }
        }
    }

    public static void findAndAddClasssByPackage(String packageName,
                                                 String packagePath, boolean recursive, Set<Class<?>> classes) {
        File dir = new File(packagePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        File[] files = dir.listFiles(file -> (recursive && file.isDirectory())
                || (file.getName().endsWith(".class")));
        for (File file : files) {
            if (file.isDirectory()) {
                findAndAddClasssByPackage(packageName + "."
                                + file.getName(), file.getAbsolutePath(), recursive,
                        classes);
            } else {
                String className = file.getName().substring(0,
                        file.getName().length() - 6);
                try {
                    classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
                } catch (ClassNotFoundException e) {
                    LOGGER.error("ClassNotFoundException", e);
                }
            }
        }
    }


}
