package top.zaqqmm.oss.feather.core.bean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-18 13:04
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {

    String value() default "";

}
