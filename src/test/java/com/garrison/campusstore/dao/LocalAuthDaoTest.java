package com.garrison.campusstore.dao;

import com.garrison.campusstore.entity.LocalAuth;
import com.garrison.campusstore.entity.PersonInfo;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocalAuthDaoTest {
    @Autowired
    private LocalAuthDao localAuthDao;
    @Autowired PersonInfoDao personInfoDao;

    @Test
    @Ignore
    public void testInsertLocalAuth(){
        LocalAuth test = new LocalAuth();
        PersonInfo personInfo = personInfoDao.queryPersonInfoByName("普通用户");
        test.setUsername(personInfo.getName());
        test.setPassword("123456");
        test.setPersonInfo(personInfo);
        test.setCreateTime(new Date());
        localAuthDao.insertLocalAuth(test);

    }
    @Test
    public void testQueryByNameandPWD(){
        String userName = "普通用户";
        String password = "123456";
        LocalAuth test = localAuthDao.queryLocalByUserNameAndPwd(userName,password);
        System.out.println(test.getUsername());
    }
}
