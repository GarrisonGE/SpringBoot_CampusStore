package com.garrison.campusstore.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PathUtil {

    private static String seperator = System.getProperty("file.separator");
    private static String winPath;
    private static String macPath;
    private static String shopPath;
    private static String headLinePath;
    private static String shopCategoryPath;

    public static String getImgBasePath(){
        String os = System.getProperty("os.name");
        String basePath="";
        if(os.toLowerCase().startsWith("win")){
            basePath = winPath;
        }else{
            basePath = macPath;
        }
        basePath = basePath.replace("/", seperator);
        return basePath;
    }
    public static String getShopImagePath(long shopId){
        String imagePath = shopPath + shopId + seperator;
        return imagePath.replace("/", seperator);
    }
    public static String getHeadLineImagePath() {
        return headLinePath.replace("/", seperator);
    }

    public static String getShopCategoryPath() {
        return shopCategoryPath.replace("/", seperator);
    }
    @Value("${win.base.path}")
    public  void setWinPath(String winPath) {
        PathUtil.winPath = winPath;
    }
    @Value("${mac.base.path}")
    public  void setMacPath(String macPath) {
        PathUtil.macPath = macPath;
    }
    @Value("${shop.relevant.path}")
    public  void setShopPath(String shopPath) {
        PathUtil.shopPath = shopPath;
    }
    @Value("${headline.relevant.path}")
    public void setHeadLinePath(String headLinePath) {
        PathUtil.headLinePath = headLinePath;
    }
    @Value("${shopcategory.relevant.path}")
    public void setShopCategoryPath(String shopCategoryPath) {
        PathUtil.shopCategoryPath = shopCategoryPath;
    }
}
