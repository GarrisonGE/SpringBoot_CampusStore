package com.garrison.campusstore.dao;

import com.garrison.campusstore.entity.ProductImg;
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
public class ProductImgDaoTest {
    @Autowired
    private ProductImgDao productImgDao;

    @Test
    @Ignore
    public void testABatchInsertProductImg() throws Exception {
        // productId为1的商品里添加两个详情图片记录
        ProductImg productImg1 = new ProductImg();
        productImg1.setImgAddr("图片1");
        productImg1.setImgDesc("测试图片1");
        productImg1.setPriority(1);
        productImg1.setCreateTime(new Date());
        productImg1.setProductId(1L);
        ProductImg productImg2 = new ProductImg();
        productImg2.setImgAddr("图片2");
        productImg2.setPriority(1);
        productImg2.setCreateTime(new Date());
        productImg2.setProductId(1L);
        List<ProductImg> productImgList = new ArrayList<ProductImg>();
        productImgList.add(productImg1);
        productImgList.add(productImg2);
        int effectedNum = productImgDao.batchInsertProductImg(productImgList);
        assertEquals(2, effectedNum);
    }

    @Test
    @Ignore
    public void testDeleteProductImgByProductId() throws Exception{
        long productId = 1;
        int effectedNum = productImgDao.deleteProductImgByProductId(productId);
        assertEquals(2,effectedNum);
    }
    @Test
    public void testQueryProductImgList(){
        long productId = 44L;
        List<ProductImg> test = productImgDao.queryProductImgList(productId);
        System.out.println(test.get(0).getProductImgId());
        assertEquals(2,test.size());

    }
}
