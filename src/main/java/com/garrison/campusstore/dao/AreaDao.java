package com.garrison.campusstore.dao;

import com.garrison.campusstore.entity.Area;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface AreaDao {
    /**
     * 列出区域列表
     * @return areaList
     */
    // interface中方法的访问修饰符默认为public，abstract
    List<Area> queryArea();
}
