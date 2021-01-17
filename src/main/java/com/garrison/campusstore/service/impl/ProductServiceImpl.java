package com.garrison.campusstore.service.impl;

import com.garrison.campusstore.dao.ProductDao;
import com.garrison.campusstore.dao.ProductImgDao;
import com.garrison.campusstore.dto.ImageHolder;
import com.garrison.campusstore.dto.ProductExecution;
import com.garrison.campusstore.entity.Product;
import com.garrison.campusstore.entity.ProductImg;
import com.garrison.campusstore.enums.ProductStateEnum;
import com.garrison.campusstore.exceptions.ProductOperationException;
import com.garrison.campusstore.service.ProductService;
import com.garrison.campusstore.util.ImageUtil;
import com.garrison.campusstore.util.PageCalculator;
import com.garrison.campusstore.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;

    //添加product
    //1. 处理缩略图，获取缩略图相对路径并赋值给product
    //2. 往tb_product写入商品信息，获取productId
    //3. 结合productId批量处理商品详情图
    //4. 将商品详情图列表批量插入tb_product_img中
    @Override
    @Transactional
    public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList) throws ProductOperationException {
        if(product != null && product.getShop() != null && product.getShop().getShopId() != null){
            //给商品信息添加上属性值
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            // 默认为上架状态
            product.setEnableStatus(1);
            // 若商品缩略图不为空则添加
            if(thumbnail != null) addThumbnail(product,thumbnail);
            try{
                //创建商品信息
                int effectedNum = productDao.insertProduct(product);
                if(effectedNum <= 0){
                    throw new ProductOperationException("创建商品失败");
                }
            }catch (Exception e){
                throw new ProductOperationException("创建商品失败" + e.toString());
            }
            //若商品详情图不为空则添加
            if(productImgHolderList != null && productImgHolderList.size() > 0){
                addProductImgList(product, productImgHolderList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS, product);
        }else{
            //product信息为空
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    @Override
    public Product getProductById(long productId) {
        return productDao.queryProductById((productId));
    }

    @Override
    @Transactional
    public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList) throws ProductOperationException {
        //空值判断
        if(product != null && product.getShop() != null && product.getShop().getShopId() != null){
            //给商品设置上默认属性
            product.setLastEditTime(new Date());
            //若商品缩略图要修改，且原有缩略图不为空，则删除原有缩略图并添加
            if(thumbnail != null){
                Product tempProduct = productDao.queryProductById(product.getProductId());
                //如果原有商品有缩略图，则删除原有图片
                if(tempProduct.getImgAddr() != null) ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
                addThumbnail(product, thumbnail);
            }
            if(productImgHolderList != null && productImgHolderList.size() > 0){
                deleteProductImgList(product.getProductId());
                addProductImgList(product, productImgHolderList);
            }
            try{
                int effectedNum = productDao.updateProduct(product);
                if(effectedNum <= 0) throw new ProductOperationException("更新商品信息失败");
                return new ProductExecution(ProductStateEnum.SUCCESS, product);
            }catch (Exception e){
                throw new ProductOperationException("更新商品信息失败:" + e.toString());
            }
        }else{
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    @Override
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
        //页码转换成数据库的行码，并调用dao层取回指定页码的商品列表
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex,pageSize);
        List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);
        int count = productDao.queryProductCount(productCondition);
        ProductExecution pe = new ProductExecution();
        pe.setProductList(productList);
        pe.setCount(count);
        return pe;
    }

    private void addThumbnail(Product product, ImageHolder thumbnail){
        //获取shop图片目录的相对路径
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail,dest);
        product.setImgAddr(thumbnailAddr);
    }
    private void addProductImgList(Product product, List<ImageHolder> productHolderImgList){
        // 获取图片存储路径，直接存放到相应店铺的文件夹底下
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> productImgList = new ArrayList<>();
        //遍历图片，添加进productImgList中
        for(ImageHolder productImgHolder: productHolderImgList){
            String imgAddr = ImageUtil.generateNormalImg(productImgHolder,dest);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(imgAddr);
            productImg.setProductId(product.getProductId());
            productImg.setCreateTime(new Date());
            productImgList.add(productImg);
        }
        //如果有图片需要添加，则执行批量添加操作
        if(productImgList.size() > 0){
            try{
                int effectedNum = productImgDao.batchInsertProductImg(productImgList);
                if(effectedNum <= 0){
                    throw new ProductOperationException("创建商品详情图片失败");
                }
            }catch (Exception e){
                throw new ProductOperationException("创建商品详情图片失败" + e.toString());
            }
        }

    }
    private void deleteProductImgList(long productId){
        List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
        for(ProductImg productImg:productImgList){
            ImageUtil.deleteFileOrPath(productImg.getImgAddr());
        }
        productImgDao.deleteProductImgByProductId(productId);
    }
}
