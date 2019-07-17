package top.zaqqmm.oss.feather.core.config;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-17 9:30
 */
public class FeatherConfig {
    private static FeatherConfig config;

    private String rootPackageName;
    private String rootPath;

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    private Integer port;

    public static FeatherConfig getInstance() {
        if (config == null) {
            config = new FeatherConfig();
        }
        return config;
    }


    public String getRootPackageName() {
        return rootPackageName;
    }

    public void setRootPackageName(Class<?> clazz) throws Exception {
        if (clazz.getPackage() == null) {
            //todo
            throw new Exception("no pachage");
        }
        this.rootPackageName = clazz.getPackage().toString();
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
