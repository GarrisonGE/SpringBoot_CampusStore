package com.garrison.campusstore.dao;

import com.garrison.campusstore.entity.Area;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaDaoTest {
    @Autowired
    private AreaDao areaDao;

    @Test
    @Ignore
    public void testQueryArea(){
        List<Area> areaList = areaDao.queryArea();
        assertEquals(5,areaList.size());
    }
    @Test
    public void testBatchDelete(){
        List<Long> areaList = new ArrayList<>();
        areaList.add(1L);
        areaList.add(2L);
        areaList.add(8L);
        int effectNum = areaDao.batchDeleteArea(areaList);
        assertEquals(3,effectNum);


    }
}
