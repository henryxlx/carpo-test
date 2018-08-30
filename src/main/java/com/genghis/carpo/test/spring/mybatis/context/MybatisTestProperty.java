package com.genghis.carpo.test.spring.mybatis.context;

import java.lang.annotation.*;

/**
 * Created by x230-think-joomla on 2018/8/29.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MybatisTestProperty {

    String configLocation() default "";

    String[] mapperLocations() default {};

    Class[] classes() default {};

    Class[] typeAliases() default {};

}
