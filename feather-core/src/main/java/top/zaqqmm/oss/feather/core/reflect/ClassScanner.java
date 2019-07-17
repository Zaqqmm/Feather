package top.zaqqmm.oss.feather.core.reflect;

import org.slf4j.Logger;
import top.zaqqmm.oss.feather.core.config.AbstractConfiguration;
import top.zaqqmm.oss.feather.core.config.ApplicationConfig;
import top.zaqqmm.oss.feather.core.log.LoggerBuilder;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-16 17:11
 */
public class ClassScanner {
    private final static Logger LOGGER = LoggerBuilder.getLogger(ClassScanner.class);


    private static Map<String, Class<?>> actionMap = null;
    private static Map<Class<?>, Integer> interceptorMap = null;

    private static Set<Class<?>> classes = null;
    private static Set<Class<?>> Feather_classes = null;

    private static List<Class<?>> configurationList = null;

    private static void baseScanner(String packageName, Set<Class<?>> set) {
        boolean recursive = true;

        String packageDirName = packageName.replace('.', '/');

        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    findAndAddClassesInPackageByFile(packageName, filePath, recursive, set);
                } else if ("jar".equals(protocol)) {
                    JarFile jar;
                    try {
                        jar = ((JarURLConnection) url.openConnection()).getJarFile();
                        Enumeration<JarEntry> entries = jar.entries();
                        while (entries.hasMoreElements()) {
                            JarEntry entry = entries.nextElement();
                            String name = entry.getName();
                            if (name.charAt(0) == '/') {
                                name = name.substring(1);
                            }
                            if (name.startsWith(packageDirName)) {
                                int idx = name.lastIndexOf('/');
                                if (idx != -1) {
                                    packageName = name.substring(0, idx).replace('/', '.');
                                }
                                if ((idx != -1) || recursive) {
                                    if (name.endsWith(".class") && !entry.isDirectory()) {
                                        String className = name.substring(packageName.length() + 1, name.length() - 6);
                                        try {
                                            set.add(Class.forName(packageName + '.' + className));
                                        } catch (ClassNotFoundException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        LOGGER.error("IOException", e);
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
    }

    public static void findAndAddClassesInPackageByFile(String packageName,
                                                        String packagePath, final boolean recursive, Set<Class<?>> classes) {
        File dir = new File(packagePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        File[] files = dir.listFiles(file -> (recursive && file.isDirectory())
                || (file.getName().endsWith(".class")));
        for (File file : files) {
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "."
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

    public static Set<Class<?>> getClasses(String packageName) {

        if (classes == null) {
            classes = new HashSet<>(32);

            baseScanner(packageName, classes);
        }

        return classes;
    }



    public static List<Class<?>> getConfiguration(String basePackageName) {
        if (configurationList == null) {
            Set<Class<?>> clsList = getClasses(basePackageName);

            clsList.add(ApplicationConfig.class);

            if (clsList == null || clsList.isEmpty()) {
                return configurationList;
            }

            configurationList = new ArrayList<>(16);
            for (Class<?> cls : clsList) {

                if (cls.getSuperclass() != AbstractConfiguration.class) {
                    continue;
                }

                configurationList.add(cls);
            }
        }
        return configurationList;
    }
}
