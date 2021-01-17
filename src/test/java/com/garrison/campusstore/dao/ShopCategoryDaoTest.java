package com.garrison.campusstore.dao;

import com.garrison.campusstore.entity.ShopCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopCategoryDaoTest{
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Test
    public void testQueryShopCategory(){

        List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(null);
        System.out.println(shopCategoryList.size());

    }
}
