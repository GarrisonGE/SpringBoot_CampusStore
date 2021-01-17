package com.garrison.campusstore.service;

import com.garrison.campusstore.dto.PersonInfoExecution;
import com.garrison.campusstore.entity.PersonInfo;

public interface PersonInfoService {
    /**
     * 根据用户Id获取personInfo信息
     *
     * @param userId
     * @return
     */
    PersonInfo getPersonInfoById(Long userId);
    /**
     * 根据用户Name获取personInfo信息
     */
    PersonInfo getPersonInfoByName(String userName);


    /**
     * 根据查询条件分页返回用户信息列表
     *
     * @param personInfoCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PersonInfoExecution getPersonInfoList(PersonInfo personInfoCondition, int pageIndex, int pageSize);

    /**
     * 根据传入的PersonInfo创建用户信息
     */
    PersonInfoExecution insertPersonInfo(PersonInfo personInfo);

    /**
     * 根据传入的PersonInfo修改对应的用户信息
     *
     * @param personInfo
     * @return
     */
    PersonInfoExecution modifyPersonInfo(PersonInfo personInfo);
}

