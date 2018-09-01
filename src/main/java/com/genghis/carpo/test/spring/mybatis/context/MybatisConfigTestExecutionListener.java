package com.genghis.carpo.test.spring.mybatis.context;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * Created by x230-think-joomla on 2018/8/30.
 */
public class MybatisConfigTestExecutionListener extends AbstractTestExecutionListener {

    private static Logger logger = LoggerFactory.getLogger(MybatisConfigTestExecutionListener.class);

    @Override
    public int getOrder() {
        return 1000;
    }

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {
        MybatisTestProperty testProperty = getMybatisTestProperty(testContext);
        ApplicationContext applicationContext = testContext.getApplicationContext();
        DataSource dataSource = getDataSource(applicationContext);
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory(testProperty, dataSource);
        if (testProperty.mapperInterfaces() != null && testProperty.mapperInterfaces().length > 0) {
            ConfigurableApplicationContext configurableApplicationContext =
                    (ConfigurableApplicationContext) applicationContext;
            DefaultListableBeanFactory beanFactory =
                    (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
            beanFactory.registerSingleton("sqlSessionFactory", sqlSessionFactory);

            for (Class mapper : testProperty.mapperInterfaces()) {
                MapperFactoryBean factoryBean = new MapperFactoryBean();
                factoryBean.setMapperInterface(mapper);
                factoryBean.setSqlSessionFactory(sqlSessionFactory);
                GenericBeanDefinition definition = new GenericBeanDefinition();
                definition.getPropertyValues().add("mapperInterface", mapper);
                definition.setBeanClass(MapperFactoryBean.class);
                definition.setScope("singleton");
                definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
                String beanName = ClassUtils.getShortName(definition.getBeanClassName());
                beanFactory.registerBeanDefinition(beanName, definition);
            }
        }
    }

    private MybatisTestProperty getMybatisTestProperty(TestContext testContext) {
        MybatisTestProperty testProperty = testContext.getTestClass().getAnnotation(MybatisTestProperty.class);
        if (testProperty == null) {
            logger.error("The annotations MybatisTestProperty for MyBatis integration testing are not found. " +
                    "Please check the test code.");
            throw new RuntimeException("The MybatisTestProperty annotation are not found");
        }
        return testProperty;
    }

    private DataSource getDataSource(ApplicationContext applicationContext) {
        DataSource dataSource;
        try {
            dataSource = applicationContext.getBean(DataSource.class);
        } catch (BeansException e) {
            logger.error("The data source for MyBatis integration testing is not found. " +
                    "Please check the configuration of the database connection." + e);
            throw new RuntimeException("JDBC data source in test context is not found");
        }
        return dataSource;
    }

    private SqlSessionFactory getSqlSessionFactory(MybatisTestProperty testProperty, DataSource dataSource) {
        SqlSessionFactory sqlSessionFactory;
        try {
            SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
            sessionFactory.setDataSource(dataSource);
            if (testProperty.configLocation() != null && testProperty.configLocation().length() > 0) {
                sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver()
                        .getResource(testProperty.configLocation()));
            } else {
                logger.warn("The configuration file location of Mybatis in your mybatis DAO test is blank! ");
            }
            if (testProperty.typeAliases() != null && testProperty.typeAliases().length > 0) {
                sessionFactory.setTypeAliases(testProperty.typeAliases());
            } else {
                logger.warn("The type Aliases used in your mybatis DAO test is empty! ");
            }
            if (testProperty.mapperLocations() != null && testProperty.mapperLocations().length > 0) {
                sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                        .getResources(StringUtils.arrayToCommaDelimitedString(testProperty.mapperLocations())));
            } else {
                logger.warn("The mapper configuration files in your mybatis DAO test is empty! ");
            }

            sqlSessionFactory = sessionFactory.getObject();
        } catch (Exception e) {
            logger.error("Incorrect parameter settings, sqlSessionFactory failed to create.", e);
            throw new RuntimeException("Mybatis sqlSessionFactory failed to create.");
        }
        return sqlSessionFactory;
    }
}
