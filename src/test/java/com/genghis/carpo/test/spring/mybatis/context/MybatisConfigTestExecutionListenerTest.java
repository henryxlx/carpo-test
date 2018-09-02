package com.genghis.carpo.test.spring.mybatis.context;

import com.genghis.carpo.basis.area.dao.AreaInfoDao;
import com.genghis.carpo.basis.area.model.Area;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.BDDMockito;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestContext;

import javax.sql.DataSource;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Created by x230-think-joomla on 2018/9/2.
 */
public class MybatisConfigTestExecutionListenerTest {

    private final MybatisConfigTestExecutionListener listener = new MybatisConfigTestExecutionListener();

    private final TestContext testContext = mock(TestContext.class);

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void missingAnnotationAtClassLevel() throws Exception {
        Class<?> clazz = MissingAnnotationAtClassLevel.class;
        BDDMockito.<Class<?>>given(testContext.getTestClass()).willReturn(clazz);
        given(testContext.getTestMethod()).willReturn(clazz.getDeclaredMethod("foo"));

        assertExceptionContains(clazz.getSimpleName());
    }

    @Test
    public void missingAllElementValueAtClassLevel() throws Exception {
        Class<?> clazz = MissingAllElementValueAtClassLevel.class;
        BDDMockito.<Class<?>>given(testContext.getTestClass()).willReturn(clazz);
        given(testContext.getTestMethod()).willReturn(clazz.getDeclaredMethod("foo"));

        assertExceptionContains(clazz.getSimpleName());
    }

    @Test
    public void missingDataSource() throws Exception {
        ApplicationContext ctx = mock(ApplicationContext.class);
        given(ctx.getAutowireCapableBeanFactory()).willReturn(mock(AutowireCapableBeanFactory.class));

        Class<?> clazz = MissingDataSource.class;
        BDDMockito.<Class<?>>given(testContext.getTestClass()).willReturn(clazz);
        given(testContext.getTestMethod()).willReturn(clazz.getDeclaredMethod("foo"));
        given(testContext.getApplicationContext()).willReturn(ctx);
        given(ctx.getBean(DataSource.class)).willThrow(new BeansException("Bean not found."){});

        assertExceptionContains(clazz.getSimpleName());
    }

    @Test
    public void mybatisSqlSessionFactoryFailed() throws Exception {
        ApplicationContext ctx = mock(ApplicationContext.class);
        DataSource ds = mock(DataSource.class);
        given(ctx.getAutowireCapableBeanFactory()).willReturn(mock(AutowireCapableBeanFactory.class));

        Class<?> clazz = MybatisSqlSessionFactoryFailed.class;
        BDDMockito.<Class<?>>given(testContext.getTestClass()).willReturn(clazz);
        given(testContext.getTestMethod()).willReturn(clazz.getDeclaredMethod("foo"));
        given(testContext.getApplicationContext()).willReturn(ctx);
        given(ctx.getBean(DataSource.class)).willReturn(ds);

        assertExceptionContains(clazz.getSimpleName());
    }


    private void assertExceptionContains(String msg) throws Exception {
        try {
            listener.prepareTestInstance(testContext);
            fail("Should have thrown an IllegalStateException.");
        } catch (IllegalArgumentException e) {
            assertTrue("Exception message should contain: " + msg, e.getMessage().contains(msg));
        } catch (IllegalStateException e) {
            // System.err.println(e.getMessage());
            assertTrue("Exception message should contain: " + msg, e.getMessage().contains(msg));
        }
    }

    // -------------------------------------------------------------------------

    static class MissingAnnotationAtClassLevel {

        public void foo() {
        }
    }

    @MybatisTestProperty
    static class MissingAllElementValueAtClassLevel {

        public void foo() {
        }
    }

    @MybatisTestProperty(configLocation = "",
            mapperLocations = {"com/genghis/carpo/basis/area/dao/areainfo.xml"},
            typeAliases = {Area.class},
            mapperInterfaces = {AreaInfoDao.class})
    static class MissingDataSource {

        public void foo() {
        }
    }

    @MybatisTestProperty(configLocation = "",
            mapperLocations = {"com/genghis/carpo/basis/area/dao/areainfo.xml"},
            typeAliases = {},
            mapperInterfaces = {AreaInfoDao.class})
    static class MybatisSqlSessionFactoryFailed {

        public void foo() {
        }
    }
}