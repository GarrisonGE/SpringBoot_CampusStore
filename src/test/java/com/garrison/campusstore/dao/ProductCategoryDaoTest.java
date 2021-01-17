package com.garrison.campusstore.dao;


import com.garrison.campusstore.entity.ProductCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest  {
    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Test
    @Ignore
    public void testQueryByShopId() throws Exception{
        long shopId = 29;
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
        System.out.println("该店铺自定义商品类别数为:" + productCategoryList.size());
    }

    @Test
    @Ignore
    public void testBatchInsertProductCategory(){

        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryName("服装类");
        productCategory.setPriority(1);
        productCategory.setCreateTime(new Date());
        productCategory.setShopId(1L);

        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setProductCategoryName("运动鞋类");
        productCategory2.setPriority(2);
        productCategory2.setCreateTime(new Date());
        productCategory2.setShopId(1L);

        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList.add(productCategory);
        productCategoryList.add(productCategory2);
        int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
        assertEquals(2, effectedNum);

    }

    @Test
    public void testDeleteProductCategory(){
        long shopId = 1L;
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
        for(ProductCategory pc: productCategoryList){
            if("服装类".equals(pc.getProductCategoryName()) || "运动鞋类".equals(pc.getProductCategoryName())){
                int effectNum = productCategoryDao.deleteProductCategory(pc.getProductCategoryId(), pc.getShopId());
                assertEquals(1,effectNum);
            }
        }

    }
}
