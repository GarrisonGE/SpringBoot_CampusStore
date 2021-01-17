package com.garrison.campusstore.dao;

import com.garrison.campusstore.entity.PersonInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonInfoDao {
    // 分页获取用户信息列表
    List<PersonInfo> queryPersonInfoList(@Param("personInfoCondition")PersonInfo personInfoCondition, @Param("rowIndex")int rowIndex, @Param("pageSize")int pageSize);

    //根据查询条件，返回复合条件的用户总数
    int queryPersonInfoCount(@Param("personInfoCondition")PersonInfo personInfoCondition);

    //根据用户Id查询用户
    PersonInfo queryPersonInfoById(long userId);

    //根据用户名字查询用户
    PersonInfo queryPersonInfoByName(String userName);

    //添加用户信息
    int insertPersonInfo(PersonInfo personInfo);

    //修改用户信息
    int updatePersonInfo(PersonInfo personInfo);


}
