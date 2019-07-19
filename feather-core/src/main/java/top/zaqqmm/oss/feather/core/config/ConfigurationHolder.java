package top.zaqqmm.oss.feather.core.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-16 19:16
 */
public class ConfigurationHolder {
    private static Map<String , AbstractConfiguration> config = new HashMap<>(16);

    public static void addConfiguration(String key,AbstractConfiguration configuration){
        config.put(key, configuration);
    }

    public static AbstractConfiguration getConfiguration(Class<? extends AbstractConfiguration> clazz){
        return config.get(clazz.getName()) ;
    }
}
