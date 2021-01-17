package com.garrison.campusstore.controller.frontend;

import com.garrison.campusstore.dto.ProductExecution;
import com.garrison.campusstore.entity.Product;
import com.garrison.campusstore.entity.ProductCategory;
import com.garrison.campusstore.entity.Shop;
import com.garrison.campusstore.service.ProductCategoryService;
import com.garrison.campusstore.service.ProductService;
import com.garrison.campusstore.service.ShopService;
import com.garrison.campusstore.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/frontend")
public class ShopDetailController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 获取店铺信息以及该店铺下面的商品类别列表
     * @param request
     * @return
     */
    @RequestMapping(value="/listshopdetailpageinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listShopDetailPageInfo(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        //获取请求中的shop id
        long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        Shop shop = null;
        List<ProductCategory> productCategoryList = null;
        if(shopId != -1){
            //根据shop id，获得shop的信息
            shop = shopService.getByShopId(shopId);
            //获取该店铺下的product 分类
            productCategoryList = productCategoryService.getProductCategoryList(shopId);
            modelMap.put("shop", shop);
            modelMap.put("productCategoryList", productCategoryList);
            modelMap.put("success", true);
        }else{
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty shopId");
        }
        return modelMap;
    }

    @RequestMapping(value="/listproductsbyshop", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listProductsByShop(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //获取页码
        int pageIndex = HttpServletRequestUtil.getInt(request,"pageIndex");
        //获取pagesize
        int pageSize = HttpServletRequestUtil.getInt(request,"pageSize");
        //获取店铺Id
        long shopId = HttpServletRequestUtil.getLong(request,"shopId");
        if((pageIndex > -1) && (pageSize > -1) &&(shopId > -1)){
            //获取请求中的商品类别Id，如果有
            long productCategoryId = HttpServletRequestUtil.getLong(request,"productCategoryId");
            //获取模糊查找的商品名
            String productName = HttpServletRequestUtil.getString(request,"productName");
            //组合查询条件
            Product productCondition = compactProductCondition4Search(shopId, productCategoryId, productName);
            ProductExecution pe = productService.getProductList(productCondition, pageIndex, pageSize);
            modelMap.put("productList", pe.getProductList());
            modelMap.put("count", pe.getCount());
            modelMap.put("success", true);
        }else{
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty PageSize or pageIndex or shopId");
        }
        return modelMap;
    }
    private Product compactProductCondition4Search(long shopId, long productCategoryId, String productName){
        Product productCondition = new Product();
        Shop shop = new Shop();
        shop.setShopId(shopId);
        productCondition.setShop(shop);
        if(productCategoryId != -1L){
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductCategoryId(productCategoryId);
            productCondition.setProductCategory(productCategory);
        }
        if(productName != null){
            productCondition.setProductName(productName);
        }
        productCondition.setEnableStatus(1);
        return productCondition;
    }


}
