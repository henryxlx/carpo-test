package com.genghis.carpo.test.dao.demo;

import com.genghis.carpo.basis.area.dao.AreaInfoDao;
import com.genghis.carpo.basis.area.model.Area;
import com.genghis.carpo.test.dao.CarpoJUnitDaoTestCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by x230-think-joomla on 2018/8/22.
 */

public class CarpoJUnitAreaInfoDaoTest extends CarpoJUnitDaoTestCase {

    private AreaInfoDao areaInfoDao;

    @Before
    public void setup() {
        areaInfoDao = getMapper(AreaInfoDao.class, "com/genghis/carpo/basis/area/dao/areainfo.xml");
    }

    @Test
    public void testAreaInfo() {
        Area area = new Area("1", "1", "1", null);
        assertEquals(1, areaInfoDao.addAreaInfo(area));
    }

}