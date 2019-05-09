package org.gy.framework.demobusiness.config.annotation;


import org.gy.framework.demobusiness.config.DynamicDataSourceType;

import java.lang.annotation.*;

/**
 * 特殊情况强制指定数据源
 */
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    DynamicDataSourceType value() default DynamicDataSourceType.MASTER;
}
