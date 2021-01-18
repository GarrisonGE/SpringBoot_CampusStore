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

    /**
     * 添加区域信息
     */
    int insertArea(Area area);
    /**
     * 更新区域信息
     */
    int updateArea(Area area);

    /**
     * 删除区域信息
     */
    int deleteArea(long areaId);
    /**
     * 批量删除区域信息
     */
    int batchDeleteArea(List<Long> areaIdList);
}
