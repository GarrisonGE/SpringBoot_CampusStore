package com.garrison.campusstore.controller.frontend;

import com.garrison.campusstore.dto.ShopExecution;
import com.garrison.campusstore.entity.Area;
import com.garrison.campusstore.entity.Shop;
import com.garrison.campusstore.entity.ShopCategory;
import com.garrison.campusstore.service.AreaService;
import com.garrison.campusstore.service.ShopCategoryService;
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
public class ShopListController {
    @Autowired
    private AreaService areaService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private ShopService shopService;

    // 根据一级商店类别id，查询二级子类商店类别
    @RequestMapping(value="/listshopspageinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listShopsPageInfo(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        //试着从前端请求中获取parentId
        long parentId = HttpServletRequestUtil.getLong(request, "parentId");
        List<ShopCategory> shopCategoryList = null;
        if(parentId != -1){
            //如果parentId存在，则取出该一级ShopCategory下的二级ShopCategory列表
            try{
                ShopCategory shopCategoryCondition = new ShopCategory();
                ShopCategory parent = new ShopCategory();
                parent.setShopCategoryId(parentId);
                shopCategoryCondition.setParent(parent);
                shopCategoryList = shopCategoryService.getShopCategoryList(shopCategoryCondition);
            }catch(Exception e){
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
        }else{
            try{
                //如果parentId不存在，则取出所有一级ShopCategory（用户在首页选择的是全部商店列表
                shopCategoryList = shopCategoryService.getShopCategoryList(null);
            }catch (Exception e){
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
        }
        modelMap.put("shopCategoryList", shopCategoryList);
        List<Area> areaList = null;
        try{
            //获取区域列表信息
            areaList = areaService.getAreaList();
            modelMap.put("areaList", areaList);
            modelMap.put("success", true);
            return modelMap;
        }catch (Exception e){
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }

    //根据查询条件，查询对应商铺列表
    @RequestMapping(value="/listshops", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listShops(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        //获取页码
        int pageIndex = HttpServletRequestUtil.getInt(request,"pageIndex");
        //获取一页需要现实的数据条数
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        if((pageIndex > -1) && (pageSize > -1)){
            //获取一级类别Id
            long parentId = HttpServletRequestUtil.getLong(request,"parentId");
            //试着获取特定二级类别Id
            long shopCategoryId = HttpServletRequestUtil.getLong(request,"shopCategoryId");
            //试着获取区域Id
            int areaId = HttpServletRequestUtil.getInt(request,"areaId");
            //试着获取模糊查询的名字
            String shopName = HttpServletRequestUtil.getString(request, "shopName");
            //获取组合之后的查询条件
            Shop shopCondition = compactShopCondition4Search(parentId, shopCategoryId, areaId, shopName);
            //根据组合的查询条件，查询商品列表
            ShopExecution se = shopService.getShopList(shopCondition, pageIndex, pageSize);
            modelMap.put("shopList", se.getShopList());
            modelMap.put("count", se.getCount());
            modelMap.put("success", true);
        }else{
            modelMap.put("success", false);
            modelMap.put("errMsg","empty pageSize or pageIndex");
        }
        return modelMap;
    }
    private Shop compactShopCondition4Search(long parentId, long shopCategoryId, int areaId, String shopName){
        Shop shopCondition = new Shop();
        if(parentId != -1L){
            //查询某个一级ShopCategory下面的所有二级Shopcategory里面的店铺列表
            ShopCategory childCategory = new ShopCategory();
            ShopCategory parentCategory = new ShopCategory();
            parentCategory.setShopCategoryId(parentId);
            childCategory.setParent(parentCategory);
            shopCondition.setShopCategory(childCategory);
        }
        //如果点全部查询，则是默认查找一级类别下的所有二级类别，如果选择了具体的二级类别，则修改shopCondition，根据二级类别进行查找
        if(shopCategoryId != -1L){
            //查询某个二级Shopcategory下面的所有店铺列表
            ShopCategory shopCategory = new ShopCategory();
            shopCategory.setShopCategoryId(shopCategoryId);
            shopCondition.setShopCategory(shopCategory);
        }
        if(areaId != -1L){
            //查询位于某个区域Id下的店铺列表
            Area area = new Area();
            area.setAreaId(areaId);
            shopCondition.setArea(area);
        }
        if(shopName != null){
            //模糊查询名字包含shopName的店铺列表
            shopCondition.setShopName(shopName);
        }
        //前端展示的都是审核成功的店铺
        shopCondition.setEnableStatus(1);
        return shopCondition;
    }


}
