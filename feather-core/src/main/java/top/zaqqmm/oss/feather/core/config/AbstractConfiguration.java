package top.zaqqmm.oss.feather.core.config;

import java.util.Properties;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-16 16:17
 */
public abstract class AbstractConfiguration {
    /**
     * file name
     */
    private String propertiesName;
    private Properties properties;

    public String getPropertiesName() {
        return propertiesName;
    }

    public void setPropertiesName(String propertiesName) {
        this.propertiesName = propertiesName;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String get(String key) {
        return properties.get(key) == null ? null : properties.get(key).toString();
    }
}
