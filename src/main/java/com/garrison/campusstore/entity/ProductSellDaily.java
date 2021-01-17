package com.garrison.campusstore.entity;

import java.util.Date;

//商品日销售类
public class ProductSellDaily {
    //销售量时间，精确到天
    private Date createTime;
    //销量
    private Integer total;
    //商品信息
    private Product product;
    //店铺信息
    private Shop shop;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
