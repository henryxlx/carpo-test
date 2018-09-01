package com.genghis.carpo.test.spring.sample;

import com.genghis.carpo.basis.area.dao.AreaInfoDao;
import com.genghis.carpo.basis.area.model.Area;
import com.genghis.carpo.test.spring.mybatis.CarpoMybatisDaoJUnit4SpringContextTests;
import com.genghis.carpo.test.spring.mybatis.context.MybatisTestProperty;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertEquals;

/**
 * Created by x230-think-joomla on 2018/8/29.
 */
@ContextConfiguration(locations = {"classpath:carpo-spring-datasource-context-test.xml"})
@MybatisTestProperty(configLocation = "",
        mapperLocations = {"com/genghis/carpo/basis/area/dao/areainfo.xml"},
        typeAliases = {Area.class},
        mapperInterfaces = {AreaInfoDao.class})
public class CarpoMybatisSimpleDaoTest extends CarpoMybatisDaoJUnit4SpringContextTests {

    @Autowired
    private AreaInfoDao areaInfoDao;

    @Test
    public void testAreaInfo() {
        Area area = new Area("1", "1", "1", null);
        assertEquals(1, areaInfoDao.addAreaInfo(area));
    }

}
