package com.garrison.campusstore.service;

import com.garrison.campusstore.dto.PersonInfoExecution;
import com.garrison.campusstore.entity.PersonInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonInfoServiceTest {

    @Autowired
    PersonInfoService personInfoService;

    @Test
    public void testInsertPersonInfoService(){
        PersonInfo user = new PersonInfo();
        user.setName("测试");
        user.setEnableStatus(1);
        user.setUserType(1);
        user.setCreateTime(new Date());
        user.setLastEditTime(new Date());
        PersonInfoExecution pe = personInfoService.insertPersonInfo(user);
        System.out.println(pe.getStateInfo());
        System.out.println(user.getUserId());
    }
}
