package com.genghis.carpo.test.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.sql.DataSource;

/**
 * Created by x230-think-joomla on 2018/8/20.
 */
@ContextConfiguration(locations= {"classpath:conf/carpo-spring-dao-test.xml", "classpath:conf/carpo-solo-mybatis-test.xml"})
public abstract class CarpoJUnitDaoTestCase extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private DataSource dataSource;

    private SqlSessionFactory factory;

    protected <T> T getMapper(Class<T> type, String mapperFileLocation) {
        if (factory == null) {
            factory = sqlSessionFactory(mapperFileLocation);
        }
        SqlSession session = factory.openSession();
        return session.getMapper(type);
    }

    protected SqlSessionFactory sqlSessionFactory(String mapperFileLocation) {
        SqlSessionFactory sqlSessionFactory;
        try {
            SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
            sessionFactory.setDataSource(dataSource);
            sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver()
                    .getResource("conf/carpo-basis-mybatis-config.xml"));
            sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                    .getResources(mapperFileLocation));

            sqlSessionFactory = sessionFactory.getObject();
        } catch (Exception e) {
            logger.error("not install sessionFactory", e);
            throw new RuntimeException("fail to create session factory");
        }

        return sqlSessionFactory;
    }

    // reserved.
}
