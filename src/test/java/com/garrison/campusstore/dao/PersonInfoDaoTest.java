package com.garrison.campusstore.dao;


import com.garrison.campusstore.entity.PersonInfo;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonInfoDaoTest {

    @Autowired
    private PersonInfoDao personInfoDao;
    @Test
    @Ignore
    public void testInsetPersonInfo(){
        PersonInfo personInfo = new PersonInfo();
        personInfo.setName("店家用户");
        personInfo.setEmail("123789123890@gmail.com");
        personInfo.setUserType(2);//普通用户权限
        personInfo.setCreateTime(new Date());
        personInfo.setEnableStatus(1);
        int effectNum = personInfoDao.insertPersonInfo(personInfo);
        assertEquals(1, effectNum);
    }
    @Test
    public void testQueryPersonInfoByName(){
        PersonInfo personInfo = personInfoDao.queryPersonInfoByName("普通用户");
        System.out.println(personInfo.getName());
        System.out.println(personInfo.getUserId());
        System.out.println(personInfo.getUserType());
    }
}
