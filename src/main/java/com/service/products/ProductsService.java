package com.service.products;

import com.bean.products.Products;
import com.bean.products.ProductsParams;
import com.model.LoginUserModel;

import java.util.Map;

public interface ProductsService {
    /**
     * 保存、修改产品信息
     * @param id
     * @param products
     * @param model
     * @return
     */
    Map<String,Object> saveOrUpdateProducts(Long id, Products products, LoginUserModel model);

    /**
     * 获取列表
     * @param model
     * @param productsParams
     * @param pageNum
     * @param pageSize
     * @return
     */
    Map<String,Object> getProductsList(LoginUserModel model, ProductsParams productsParams, Long pageNum, Long pageSize);
}
