package com.garrison.campusstore.controller.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/shopadmin", method={RequestMethod.GET})
public class ShopAdminController {
    @RequestMapping(value="/shopoperation")
    private String shopOperation(HttpServletRequest request){
        return "/shop/shopoperation";
    }
    @RequestMapping(value="/shoplist")
    private String shopList(){
        return "/shop/shoplist";
    }

    @RequestMapping(value="/shopmanagement")
    private String shopManagement(){
        return "/shop/shopmanagement";
    }

    @RequestMapping(value="/productcategorymanagement", method=RequestMethod.GET)
    private String productCategoryManage(){
        return "/shop/productcategorymanagement";
    }

    @RequestMapping(value="productoperation")
    public String productOperation(){
        return "/shop/productoperation";
    }

    @RequestMapping(value="productmanagement")
    public String productManagement(){
        return "/shop/productmanagement";
    }


}
