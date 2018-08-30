package com.genghis.carpo.basis.dict.model;

import org.apache.ibatis.type.Alias;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DictValuesVO.java
 * 本类是字典值信息类
 *
 * @version 2.0.1
 */
@Alias("DictValuesVO")
public class DictValues {
    //字典名称
    private String dictName;
    //字典代码
    private String dictCode;
    //字典值
    private String dictValue;
    //状态
    private String state;
    //字典代码数组，用来和dictName一起查询字典值
    private String[] dictCodes;
    //参数标识 1：系统自带参数 0：客户可维护参数
    private int sysFlag;
    public static Map<String, List<DictValues>> dictValuesMap;
    public static Map<String, Map<String, DictValues>> dictValues;

    public DictValues() {
        this.dictName = "";
        this.dictCode = "";
        this.dictValue = "";
        this.sysFlag = 1;
        this.dictCodes = null;
    }

    /**
     * 通过字典名称取得字典值列表
     *
     * @param dictName
     * @return List
     */
    public static List<DictValues> getDictValuesByName(String dictName) {
        List<DictValues> list = new ArrayList<>();
        List<DictValues> temp = dictValuesMap.get(dictName);
        if (temp != null) {
            list.addAll(temp);
        }
        return list;
    }

    /**
     * 通过字典名称取得字典值map
     *
     * @param dictName 字典名称
     * @return List
     */
    public static Map<String, DictValues> getDictValuesMapByName(String dictName) {
        Map<String, DictValues> map = new HashMap<>();
        map.putAll(dictValues.get(dictName));
        return map;
    }

    /**
     * 根据dictName，dictCode获取dictValue
     *
     * @param dictName 字典名称
     * @param dictCode 字典代码
     * @return dictValue
     */
    public static String getDictValue(String dictName, String dictCode) {
        try {
            return getDictValuesMapByName(dictName).get(dictCode).getDictValue();
        } catch (Exception e) {
            return "";
        }
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String[] getDictCodes() {
        return dictCodes;
    }

    public void setDictCodes(String[] dictCodes) {
        this.dictCodes = dictCodes;
    }

    public int getSysFlag() {
        return sysFlag;
    }

    public void setSysFlag(int sysFlag) {
        this.sysFlag = sysFlag;
    }

    public String getSysFlagShow() {
        return DictValues.getDictValue("sys_flag", String.valueOf(sysFlag));
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
