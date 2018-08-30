package com.genghis.carpo.test.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 * Created by x230-think-joomla on 2018/8/28.
 */
public class MapperBeanRegistrar implements BeanDefinitionRegistryPostProcessor {

    private BeanDefinitionRegistry beanDefinitionRegistry;

    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // reserved.
    }

    public void registerMybatisMapperToSpringBean(String mybatisMapperInterface, SqlSessionFactory sqlSessionFactory) {
        if (mybatisMapperInterface == null || sqlSessionFactory == null) {
            return;
        }
        Collection<Class<?>> mappers = sqlSessionFactory.getConfiguration().getMapperRegistry().getMappers();
        for (Class<?> mapper : mappers) {
            if (mapper.getName().equals(mybatisMapperInterface)) {
                MapperFactoryBean factoryBean = new MapperFactoryBean();
                factoryBean.setMapperInterface(mapper);
                factoryBean.setSqlSessionFactory(sqlSessionFactory);
                GenericBeanDefinition definition = new GenericBeanDefinition();
                definition.getPropertyValues().add("mapperInterface", mapper);
                definition.setBeanClass(MapperFactoryBean.class);
                definition.setScope("singleton");
                definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
                String beanName = ClassUtils.getShortName(definition.getBeanClassName());
                if (!beanDefinitionRegistry.isBeanNameInUse(beanName)) {
                    beanDefinitionRegistry.registerBeanDefinition(beanName, definition);
                }
                break;
            }
        }
    }
}
