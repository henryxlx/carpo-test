package com.genghis.carpo.test.dao;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * Created by x230-think-joomla on 2018/8/20.
 */
@ContextConfiguration(locations= {"classpath:conf/carpo-spring-dao-test.xml", "classpath:conf/carpo-basis-mybatis-test.xml"})
public abstract class CarpoBasisDaoTestCase extends AbstractTransactionalJUnit4SpringContextTests {

    // reserved.
}
