package com.garrison.campusstore.service;

import com.garrison.campusstore.entity.Area;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaServiceTest {
    @Autowired
    private AreaService areaService;
    @Autowired
    private CacheService cacheService;
    @Test
    public void testGetAreaList() throws IOException {
        List<Area> areaList = areaService.getAreaList();
        Assert.assertEquals("东苑", ((Area)areaList.get(0)).getAreaName());
        AreaService var10001 = this.areaService;
        this.cacheService.removeFromCache("arealist");
    }
}
