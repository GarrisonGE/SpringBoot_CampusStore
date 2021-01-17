package com.garrison.campusstore.dto;

import com.garrison.campusstore.entity.ProductCategory;
import com.garrison.campusstore.enums.ProductCategoryStateEnum;

import java.util.List;

public class ProductCategoryExecution {
    //结果状态
    private int state;
    //状态表示
    private String stateInfo;
    //增删改时需要
    private List<ProductCategory> productCategoryList;

    //操作失败的时候使用的构造器
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum, List<ProductCategory> productCategoryList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.productCategoryList = productCategoryList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }
}
