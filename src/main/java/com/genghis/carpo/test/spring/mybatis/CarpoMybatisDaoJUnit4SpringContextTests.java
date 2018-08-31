package com.genghis.carpo.test.spring.mybatis;

import com.genghis.carpo.test.spring.mybatis.context.MybatisConfigTestExecutionListener;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;

/**
 * Created by x230-think-joomla on 2018/8/30.
 */
@ContextConfiguration(locations = {"classpath:carpo-spring-datasource-context-test.xml"})
@RunWith(SpringRunner.class)
@TestExecutionListeners({MybatisConfigTestExecutionListener.class,
        DirtiesContextBeforeModesTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class })
public class CarpoMybatisDaoJUnit4SpringContextTests {
}