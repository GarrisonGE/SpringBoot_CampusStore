package com.garrison.campusstore.service;

import com.garrison.campusstore.dto.ImageHolder;
import com.garrison.campusstore.dto.ShopCategoryExecution;
import com.garrison.campusstore.entity.ShopCategory;

import java.util.List;

public interface ShopCategoryService {

    public static String SCLISTKEY = "shopcategorylist";

    /**
     * 根据查询条件查询对应的商品类别
     * @param shopcategoryCondition
     * @return
     */
    List<ShopCategory> getShopCategoryList(ShopCategory shopcategoryCondition);
    /**
     * 添加店铺类别，并存储店铺类别图片
     *
     * @param shopCategory
     * @param thumbnail
     * @return
     */
    ShopCategoryExecution addShopCategory(ShopCategory shopCategory, ImageHolder thumbnail);

    /**
     * 修改店铺类别
     *
     * @param shopCategory
     * @param thumbnail
     * @return
     */
    ShopCategoryExecution modifyShopCategory(ShopCategory shopCategory, ImageHolder thumbnail);

    /**
     * 根据Id返回店铺类别信息
     *
     * @param shopCategoryId
     * @return
     */
    ShopCategory getShopCategoryById(Long shopCategoryId);

    /**
     * 删除店铺类别
     * @param shopCategoryId
     * @return
     */
    ShopCategoryExecution removeShopCategory(long shopCategoryId);

    /**
     * 批量删除店铺类别
     * @param shopCategoryIdList
     * @return
     */
    ShopCategoryExecution removeShopCategoryList(List<Long> shopCategoryIdList);


}
