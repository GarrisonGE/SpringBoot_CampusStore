package com.garrison.campusstore.dao;

import com.garrison.campusstore.entity.Product;
import com.garrison.campusstore.entity.ProductCategory;
import com.garrison.campusstore.entity.Shop;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductDaoTest{
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;

    @Test
    @Ignore
    public void testAInsertProduct() throws Exception {
        Shop shop1 = new Shop();
        shop1.setShopId(1L);
        ProductCategory pc1 = new ProductCategory();
        pc1.setProductCategoryId(1L);
        // 初始化三个商品实例并添加进shopId为1的店铺里，
        // 同时商品类别Id也为1
        Product product1 = new Product();
        product1.setProductName("测试1");
        product1.setProductDesc("测试Desc1");
        product1.setImgAddr("test1");
        product1.setPriority(1);
        product1.setEnableStatus(1);
        product1.setCreateTime(new Date());
        product1.setLastEditTime(new Date());
        product1.setShop(shop1);
        product1.setProductCategory(pc1);
        Product product2 = new Product();
        product2.setProductName("测试2");
        product2.setProductDesc("测试Desc2");
        product2.setImgAddr("test2");
        product2.setPriority(2);
        product2.setEnableStatus(0);
        product2.setCreateTime(new Date());
        product2.setLastEditTime(new Date());
        product2.setShop(shop1);
        product2.setProductCategory(pc1);
        Product product3 = new Product();
        product3.setProductName("test3");
        product3.setProductDesc("测试Desc3");
        product3.setImgAddr("test3");
        product3.setPriority(3);
        product3.setEnableStatus(1);
        product3.setCreateTime(new Date());
        product3.setLastEditTime(new Date());
        product3.setShop(shop1);
        product3.setProductCategory(pc1);
        // 判断添加是否成功
        int effectedNum = productDao.insertProduct(product1);
        assertEquals(1, effectedNum);
        effectedNum = productDao.insertProduct(product2);
        assertEquals(1, effectedNum);
        effectedNum = productDao.insertProduct(product3);
        assertEquals(1, effectedNum);
    }

    @Test
    @Ignore
    public void testCQueryProductByProductId() throws Exception {
        long productId = 44;
        // 初始化两个商品详情图实例作为productId为1的商品下的详情图片
        // 批量插入到商品详情图表中

        // 查询productId为1的商品信息并校验返回的详情图实例列表size是否为2
        Product product = productDao.queryProductById(productId);
        System.out.println("product id:" + product.getProductId() + "\n");
        System.out.println("product name:" + product.getProductName() + "\n");
        System.out.println("product img addr:" + product.getImgAddr() + "\n");
        System.out.println("product priority:" + product.getPriority()+ "\n");
        System.out.println("product Img priority:" + product.getProductImgList().get(0).getPriority());


    }

    @Test
    @Ignore
    public void testDUpdateProduct() throws Exception {
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(28L);
        product.setPriority(50);
        product.setProductId(7L);
        product.setShop(shop);
        // 修改productId为1的商品的名称
        // 以及商品类别并校验影响的行数是否为1
        int effectedNum = productDao.updateProduct(product);
        assertEquals(1, effectedNum);
    }

    @Test
    @Ignore
    public void testQueryProductList(){
        Product productCondition = new Product();
        //分页查询，预期返回
        List<Product> productList = productDao.queryProductList(productCondition,0, 3);
        assertEquals(3, productList.size());
        //查询总数
        int count = productDao.queryProductCount(productCondition);
        System.out.println(count);
        assertEquals(16,count);
        //使用商品名称模糊查询
        productCondition.setProductName("测试");
        productList = productDao.queryProductList(productCondition,0,3);
        assertEquals(1,productList.size());
        count = productDao.queryProductCount(productCondition);
        assertEquals(1,count);
    }

    @Test
    public void testUpdateProductCategoryToNull(){
        int effectedNum = productDao.updateProductCategoryToNull(2L);
        assertEquals(2,effectedNum);
    }



}
