package com.garrison.campusstore.service;

import com.garrison.campusstore.dto.ImageHolder;
import com.garrison.campusstore.dto.ProductExecution;
import com.garrison.campusstore.entity.Product;
import com.garrison.campusstore.exceptions.ProductOperationException;

import java.util.List;

public interface ProductService {
    /**
     * 增加商品
     * @param product 商品详情信息
     * @param thumbnail 缩略图的ImageHolder， 保存缩略图文件流和缩略图名称
     * @param productImgHolderList 详细照片的ImageHolder集合，保存详细照片的文件流和文件名
     * @return
     * @throws ProductOperationException
     */
    ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList) throws ProductOperationException;

    /**
     * 通过商品Id查询唯一的商品信息
     * @param productId
     * @return
     */
    Product getProductById(long productId);

    /**
     * 修改商品信息以及照片处理
     * @param product
     * @param thumbnail
     * @param productImgHolderList
     * @return
     * @throws ProductOperationException
     */
    ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList) throws ProductOperationException;

    /**
     * 查询商品列表并分页，可输入的条件有：商品名（模糊），商品状态，店铺Id，商品类别
     * @param productCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);
}
