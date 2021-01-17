package com.garrison.campusstore.dao;

import com.garrison.campusstore.entity.LocalAuth;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.jni.Local;

import java.util.Date;

public interface LocalAuthDao {

    /**
     * 判断账号和密码，登陆用
     */
    LocalAuth queryLocalByUserNameAndPwd(@Param("username") String username, @Param("password") String password);
    /**
     * 根据用户Id，查询对应localauth
     */
    LocalAuth queryLocalByUserId(@Param("userId") long userId);
    /**
     * 添加平台账户
     */
    int insertLocalAuth(LocalAuth localAuth);

    /**修改密码
     *
     */
    int updateLocalAuth(@Param("userId") Long userId, @Param("username") String username,
                        @Param("password") String password, @Param("newPassword") String newPassword, @Param("lastEditTime")Date lastEditTime);

}
