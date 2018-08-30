package com.genghis.carpo.test.dao.demo;

import com.genghis.carpo.basis.area.dao.AreaInfoDao;
import com.genghis.carpo.basis.area.model.Area;
import com.genghis.carpo.test.dao.CarpoBasisDaoTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by x230-think-joomla on 2018/8/22.
 */
public class CarpoBasisProjectAreaInfoDaoTest extends CarpoBasisDaoTestCase {

    @Autowired
    private AreaInfoDao areaInfoDao;

    @Test
    public void testAreaInfo() {
        Area area = new Area("1", "1", "1", null);
        assertEquals(1, areaInfoDao.addAreaInfo(area));
    }

}