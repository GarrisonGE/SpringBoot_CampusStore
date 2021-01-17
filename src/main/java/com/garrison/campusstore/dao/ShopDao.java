package com.garrison.campusstore.dao;

import com.garrison.campusstore.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {
    /**
     * add new shop
     * @param shop
     * @return
     */
    int insertShop(Shop shop);
    /**
     * update shop information
     */
    int updateShop(Shop shop);

    /**
     * get the information about shop by query shop id
     * @param shopId
     * @return shop with query id
     */
    Shop queryByShopId(long shopId);

    /**
     *  分页查询店铺，可输入条件有：店铺名（模糊），店铺状态，店铺类别，区域Id，owner
     * @param rowIndex: 从第几行开始取
     * @param pagesize: 返回的条数
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,
                             @Param("rowIndex") int rowIndex,@Param("pageSize") int pagesize);
    // 获取所有符合条件的数据的数量
    int queryShopCount(@Param("shopCondition") Shop shopCondition);
}
