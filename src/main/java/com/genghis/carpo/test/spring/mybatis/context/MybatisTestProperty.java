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

    /**
     * Location of the MyBatis {@code SqlSessionFactory} config file. A typical value is
     * "WEB-INF/mybatis-configuration.xml".
     */
    String configLocation() default "";

    /**
     * Locations of MyBatis mapper files that are going to be merged into the configuration at runtime.
     *
     * This is an alternative to specifying "&lt;sqlmapper&gt;" entries in an MyBatis config file.
     * This property being based on Spring's resource abstraction also allows for specifying
     * resource patterns here: e.g. "classpath*:sqlmap/*-mapper.xml".
     */
    String[] mapperLocations() default {};

    /**
     * The mapper interfaces of the MyBatis mapper
     */
    Class[] mapperInterfaces() default {};

    /**
     * List of type aliases to register. They can be annotated with {@code Alias}
     */
    Class[] typeAliases() default {};

}
