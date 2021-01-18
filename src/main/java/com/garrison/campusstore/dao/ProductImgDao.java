package com.garrison.campusstore.dao;

import com.garrison.campusstore.entity.ProductImg;

import java.util.List;

public interface ProductImgDao {

    /**
     * 批量添加商品详情图片
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    /**
     * 删除指定商品下的所有详情图
     * @param productId
     * @return 影响的行数
     */
    int deleteProductImgByProductId(long productId);

    /**
     * 根据商品Id 获取商品详情图
     * @param productId
     * @return
     */
    List<ProductImg> queryProductImgList(long productId);
}
