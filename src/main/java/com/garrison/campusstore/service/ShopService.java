package com.garrison.campusstore.service;

import com.garrison.campusstore.dto.ImageHolder;
import com.garrison.campusstore.dto.ShopExecution;
import com.garrison.campusstore.entity.Shop;
import com.garrison.campusstore.exceptions.ShopOperationException;

public interface ShopService {
    // 添加shop
    public ShopExecution addShop(Shop shop, ImageHolder thumbnail);
    // 查询shop
    public Shop getByShopId(long shopId);
    //更新shop
    public ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
    //根据shopCondition分页返回相应店铺列表
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pagesize);
}
