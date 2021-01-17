package com.garrison.campusstore.interceptor.shopadmin;

import com.garrison.campusstore.entity.Shop;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShopPermissionInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从session中获取当前选择的店铺
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        //从session中获取当前用户可操作的店铺列表
        List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
        if(currentShop != null && shopList != null){
            for(Shop shop: shopList){
                //如果当前店铺在可操作的列表里则返回true
                if(shop.getShopId() == currentShop.getShopId()){
                    return true;
                }
            }
        }
        return false;
    }
}
