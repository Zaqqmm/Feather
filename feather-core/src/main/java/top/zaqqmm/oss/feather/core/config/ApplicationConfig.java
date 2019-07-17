package top.zaqqmm.oss.feather.core.config;

import top.zaqqmm.oss.feather.core.constants.FeatherConstants;

import static top.zaqqmm.oss.feather.core.constants.FeatherConstants.SystemProperties.APPLICATION_PROPERTIES;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-12 16:58
 */
public class ApplicationConfig extends AbstractConfiguration {


    public ApplicationConfig(){
        super.setPropertiesName(APPLICATION_PROPERTIES);
    }

}
