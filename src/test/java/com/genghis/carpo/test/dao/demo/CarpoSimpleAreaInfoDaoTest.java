package com.genghis.carpo.test.dao.demo;

import com.genghis.carpo.basis.area.dao.AreaInfoDao;
import com.genghis.carpo.basis.area.model.Area;
import com.genghis.carpo.test.dao.CarpoMybatisConfig;
import com.genghis.carpo.test.dao.CarpoSimpleDaoTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import static org.junit.Assert.assertEquals;

/**
 * Created by x230-think-joomla on 2018/8/22.
 */
@TestPropertySource(properties = {CarpoMybatisConfig.MYBATIS_CONFIG_LOCATION + "=conf/carpo-basis-mybatis-config.xml",
        CarpoMybatisConfig.MYBATIS_MAPPER_LOCATIONS + "=com/genghis/carpo/basis/area/dao/areainfo.xml",
        CarpoMybatisConfig.MYBATIS_MAPPER_INTERFACE + "=com.genghis.carpo.basis.area.dao.AreaInfoDao"})
public class CarpoSimpleAreaInfoDaoTest extends CarpoSimpleDaoTestCase {

    @Autowired
    private AreaInfoDao areaInfoDao;

    @Test
    public void testAreaInfo() {
        Area area = new Area("1", "1", "1", null);
        assertEquals(1, areaInfoDao.addAreaInfo(area));
    }

}