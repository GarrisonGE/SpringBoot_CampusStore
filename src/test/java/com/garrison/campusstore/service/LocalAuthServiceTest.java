package com.garrison.campusstore.service;

import com.garrison.campusstore.dto.LocalAuthExecution;
import com.garrison.campusstore.dto.PersonInfoExecution;
import com.garrison.campusstore.entity.LocalAuth;
import com.garrison.campusstore.entity.PersonInfo;
import com.garrison.campusstore.enums.PersonInfoStateEnum;
import org.apache.tomcat.jni.Local;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocalAuthServiceTest {
    @Autowired
    LocalAuthService localAuthService;
    @Autowired
    PersonInfoService personInfoService;

    @Test
    @Ignore
    public void testLocalAuthService(){
        LocalAuth test = localAuthService.getLocalAuthByUsernameAndPwd("普通用户", "123456");
        System.out.println(test.getPersonInfo().getUserType());
    }
    @Test
    public void testInsertLocal(){
        PersonInfo user = new PersonInfo();
        user.setName("店家用户");
        user.setEnableStatus(1);
        user.setUserType(2);
        user.setCreateTime(new Date());
        user.setLastEditTime(new Date());
        PersonInfoExecution pe = personInfoService.insertPersonInfo(user);
            //用户信息注册成功，将local auth与其绑定
        LocalAuth localAuth = new LocalAuth();
        localAuth.setUsername(pe.getPersonInfo().getName());
        localAuth.setPassword("password");
        localAuth.setPersonInfo(user);
        localAuth.setCreateTime(new Date());
        LocalAuthExecution le = localAuthService.bindLocalAuth(localAuth);
    }
}
