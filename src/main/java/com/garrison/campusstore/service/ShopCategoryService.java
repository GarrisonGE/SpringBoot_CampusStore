package com.garrison.campusstore.service;

import com.garrison.campusstore.entity.ShopCategory;

import java.util.List;

public interface ShopCategoryService {

    public static String SCLISTKEY = "shopcategorylist";
    List<ShopCategory> getShopCategoryList(ShopCategory shopcategoryCondition);


}
