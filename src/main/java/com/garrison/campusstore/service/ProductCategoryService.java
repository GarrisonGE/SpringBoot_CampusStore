package com.garrison.campusstore.service;

import com.garrison.campusstore.dto.ProductCategoryExecution;
import com.garrison.campusstore.entity.ProductCategory;
import com.garrison.campusstore.exceptions.ProductCategoryOperationException;

import java.util.List;

public interface ProductCategoryService {
    /**
     * 查询指定的店铺下的所有商品类别信息
     * @param shopId
     * @return
     */
    List<ProductCategory> getProductCategoryList(long shopId);

    /**
     * 批量添加商品类别，返回值为封装的dto，ProductCategoryExecution
     * @param productCategoryList
     * @return
     * @throws ProductCategoryOperationException
     */

    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException;

    /**
     * 将此类别下的商品里的类别id是指为空，再删除商品类别
     * @param productCategoryId
     * @param shopId
     * @return
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException;
}
