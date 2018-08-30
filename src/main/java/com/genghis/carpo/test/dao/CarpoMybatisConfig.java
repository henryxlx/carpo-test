package com.genghis.carpo.test.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Created by x230-think-joomla on 2018/8/27.
 */
@Configuration
@PropertySource("classpath:conf/carpo-spring-dao-test.properties")
public class CarpoMybatisConfig {

    public static final String MYBATIS_CONFIG_LOCATION = "mybatis.configLocaiton";

    public static final String MYBATIS_MAPPER_LOCATIONS = "mybatis.mapperLocations";

    public static final String MYBATIS_MAPPER_INTERFACE = "mybatis.mapperInterface";

    private static Logger logger = LoggerFactory.getLogger(CarpoMybatisConfig.class);

    @Autowired
    private MapperBeanRegistrar beanRegistrar;

    @Autowired
    private Environment env;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    @Bean
    public DataSource dataSource() {
        org.apache.tomcat.jdbc.pool.DataSource myDataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        myDataSource.setPassword(env.getProperty("jdbc.password"));
        myDataSource.setUsername(env.getProperty("jdbc.username"));
        myDataSource.setUrl(env.getProperty("jdbc.url"));
        myDataSource.setDriverClassName(env.getProperty("jdbc.driver"));
        myDataSource.setMaxActive(Integer.parseInt(env.getProperty("jdbc.max.active")));
        return myDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory;
        try {
            SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
            sessionFactory.setDataSource(dataSource);
            sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver()
                    .getResource(env.getProperty(MYBATIS_CONFIG_LOCATION)));
            sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                    .getResources(env.getProperty(MYBATIS_MAPPER_LOCATIONS)));

            sqlSessionFactory = sessionFactory.getObject();
        } catch (Exception e) {
            logger.error("not install sessionFactory", e);
            throw new RuntimeException("fail to create session factory");
        }
        if (sqlSessionFactory != null) {
            beanRegistrar.registerMybatisMapperToSpringBean(env.getProperty(MYBATIS_MAPPER_INTERFACE),
                    sqlSessionFactory);
        }
        return sqlSessionFactory;
    }

    @Bean
    public DataSourceTransactionManager transaction() {
        return new DataSourceTransactionManager(dataSource);
    }

}
