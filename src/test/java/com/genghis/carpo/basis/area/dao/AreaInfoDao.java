/*
* AreaInfoDao.java
* Created on  2014-3-2 上午1:48
* 版本       修改时间          作者      修改内容
* V1.0.1    2014-3-2        gaoxinyu    初始版本
*
*/
package com.genghis.carpo.basis.area.dao;

import com.genghis.carpo.basis.area.model.Area;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author gaoxinyu
 * @version 1.0.1
 */
@Repository
public interface AreaInfoDao {
    List<Area> getAreaInfoList();

    int addAreaInfo(Area area);

    void modifyAreaName(Area area);

    void updateDictAreaIsLeaf(Area area);

    //最大的sortBy +1
    String getMaxDictAreaSortBy(String pid);

    int getAreaNum(String idForDelete);

    void deleteAreaInfo(String idForDelete);

    int checkAreaName(Area area);

    void modifyAreaSortBy(Area area);

    void modifyAreaState(Area area);

    int getChildNodeOne(Area area);

    Area getAreaInfoById(String id);

    List<Map<String, String>> getAreaTreeList();

    Area getMaxLevelArea();

    Area getAreaIsLeafByName(String areaName);

    int geMaxAreaLevel();

}
