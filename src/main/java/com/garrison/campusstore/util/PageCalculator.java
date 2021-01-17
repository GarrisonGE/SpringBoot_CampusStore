package com.garrison.campusstore.util;

public class PageCalculator {
    public static int calculateRowIndex(int pageIndex, int pageSize){
        //实现分页查询转换
        return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
    }
}
