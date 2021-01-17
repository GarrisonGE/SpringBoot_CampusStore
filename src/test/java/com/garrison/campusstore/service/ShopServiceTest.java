package com.garrison.campusstore.service;

import com.garrison.campusstore.dto.ImageHolder;
import com.garrison.campusstore.dto.ShopExecution;
import com.garrison.campusstore.entity.Area;
import com.garrison.campusstore.entity.PersonInfo;
import com.garrison.campusstore.entity.Shop;
import com.garrison.campusstore.entity.ShopCategory;
import com.garrison.campusstore.enums.ShopStateEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopServiceTest {
    @Autowired
    private ShopService shopService;

    @Test
    @Transactional
    public void testAddShop() throws FileNotFoundException {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(8L);
        area.setAreaId(3);
        shopCategory.setShopCategoryId(10L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺3");
        shop.setShopDesc("test3");
        shop.setShopAddr("test3");
        shop.setPhone("test3");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg = new File("/Users/jiahaoge/IdeaProjects/SpringBoot_CampusStore/src/main/resources/image/alittle.png");
        InputStream is = new FileInputStream(shopImg);
        ImageHolder imageHolder = new ImageHolder(shopImg.getName(),is);
        ShopExecution se = shopService.addShop(shop, imageHolder);
        assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
    }
}
