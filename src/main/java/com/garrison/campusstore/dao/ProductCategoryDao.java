package com.garrison.campusstore.dao;

import com.garrison.campusstore.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryDao {
    // get the product category had by target shop
    List<ProductCategory> queryProductCategoryList(long shopId);
    //批量新增商品类别，返回影响行数
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);
    //删除商品类别，返回影响行数
    int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);
}
