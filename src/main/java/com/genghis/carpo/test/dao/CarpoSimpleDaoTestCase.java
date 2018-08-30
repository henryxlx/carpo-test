package com.genghis.carpo.test.dao;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * Created by x230-think-joomla on 2018/8/28.
 */
@ContextConfiguration(classes = {CarpoMybatisConfig.class, MapperBeanRegistrar.class})
public class CarpoSimpleDaoTestCase extends AbstractTransactionalJUnit4SpringContextTests {

}
