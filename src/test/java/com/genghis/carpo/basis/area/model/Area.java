/**
 * AreaInfoVO.java
 * 功能：物料类型维护VO
 * 类名: AreaInfoVO
 * 版本       修改时间       作者    修改内容
 * ---------------------------------------------------
 * V1.0.1    2010-04-15     李鹏    初始版本
 * Copyright (c) 2010 carpo Team All Rights Reserved
 */
package com.genghis.carpo.basis.area.model;


import com.genghis.carpo.basis.dict.model.DictValues;
import org.apache.ibatis.type.Alias;

/**
 * 本类地区信息维护VO
 *
 * @version 1.0.1 2010.04.15
 */
@Alias("AreaInfoVO")
public class Area {
    //地区id
    private String areaId;
    //地区名称
    private String areaName;
    private String state;
    private String pid;
    private String level;
    private String sortBy;
    private String isLeaf;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Area() {

    }

    public Area(String areaName, String pid, String level, String isLeaf) {
        this.areaName = areaName;
        this.pid = pid;
        this.level = level;
        this.isLeaf = isLeaf;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(String leaf) {
        isLeaf = leaf;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateShow() {
        return DictValues.getDictValue("state", getState());
    }
}
