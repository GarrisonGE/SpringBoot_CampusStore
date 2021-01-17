package com.garrison.campusstore.dao;


import com.garrison.campusstore.entity.HeadLine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest
public class HeadLineDaoTest {
    @Autowired
    private HeadLineDao headLineDao;
    @Test
    public void testQueryHeadLine(){
        HeadLine headLineCondition = new HeadLine();
        List<HeadLine> headLineList = headLineDao.queryHeadLine(headLineCondition);
        assertEquals(4, headLineList.size());
    }

}
