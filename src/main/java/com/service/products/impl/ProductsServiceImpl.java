package com.service.products.impl;

import com.alibaba.druid.util.StringUtils;
import com.bean.products.Products;
import com.bean.products.ProductsParams;
import com.bean.user.User;
import com.exception.ProductsException;
import com.github.pagehelper.PageHelper;
import com.mapper.ProductsMapper;
import com.model.LoginUserModel;
import com.service.products.ProductsService;
import com.utils.OperateBeanUtil;
import com.utils.PageInfo;
import com.utils.Tips;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("productsService")
public class ProductsServiceImpl implements ProductsService {

    @Resource
    public ProductsMapper productsMapper;
    @Override
    public Map<String, Object> saveOrUpdateProducts(Long id, Products products, LoginUserModel model) {
        Map<String, Object> map = new HashMap<>();
        if(model == null) {
            map.put(Tips.REPLY_CODE, Tips.NO_LOGIN);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.NO_LOGIN));
            return map;

        }
        if(id == null) {
            map = this.saveProducts(model, products);
        }else {
            map = this.updateProducts(id, model, products);
        }

        return map;
    }

    @Override
    public Map<String, Object> getProductsList(LoginUserModel model, ProductsParams productsParams, Long pageNum, Long pageSize) {
        Map<String, Object> map = new HashMap<>();
//        if(model == null) {
//            map.put(Tips.REPLY_CODE, Tips.NO_LOGIN);
//            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.NO_LOGIN));
//            return map;
//        }
//        User loginUser = model.getUser();
        if(pageNum == null ) {
            pageNum = 1L;
        }
        if(pageSize == null) {
            pageSize = 5L;
        }
        //mapper分页
        PageHelper.startPage(Integer.parseInt(pageNum.toString()), Integer.parseInt(pageSize.toString()), true);
        List<Products> productsList = productsMapper.findProductsListByParams(productsParams);
        PageInfo<Products> productsPageInfo = new PageInfo(productsList);

        map.put("productsPageInfo", productsPageInfo);
        map.put(Tips.REPLY_CODE, Tips.ACTION_SUCCESS);
        map.put(Tips.REPLY_MSG, Tips.getTip(Tips.ACTION_SUCCESS));

        return map;
    }

    private Map<String,Object> saveProducts(LoginUserModel model, Products products) {

        Map<String, Object> map = new HashMap<>();
        User loginUser = model.getUser();
        //判断当前登录用户是否有权限
        //TODO


        //验证参数
        try {
            products = this.validParams(products);

        } catch (ProductsException e) {
            e.printStackTrace();
            map.put(Tips.REPLY_CODE, Long.valueOf(e.getMessage()));
            map.put(Tips.REPLY_MSG, Tips.getTip(Long.valueOf(e.getMessage())));
            return  map;
        }

        Timestamp nowData = new Timestamp(new Date().getTime());
        products.setAddTime(nowData);
        //添加人設置為當前登錄用戶的id
        products.setAddUserId(loginUser.getId());
        productsMapper.addProducts(products);

        map.put("products", products);
        map.put(Tips.REPLY_CODE, Tips.ACTION_SUCCESS);
        map.put(Tips.REPLY_MSG, Tips.getTip(Tips.ACTION_SUCCESS));


        return map;
    }

    private Products validParams(Products products) throws ProductsException {
        if(products == null) {

            return products;
        }
        //获取产品编码
        String productNo = products.getProductNO();
        if(productNo == null) {
            throw new ProductsException(Tips.PRODUCTS_PRODUCTSNO_NULL);
        }
        //获取类型id
        Long typeId = products.getTypeId();
        if(typeId == null) {
            throw new ProductsException(Tips.PRODUCTS_TYPEID_NULL);
        }
        //获取数量
        Integer quantity = products.getQuantity();
        if(quantity == null) {
            throw new ProductsException(Tips.PRODUCTS_QUANTITY_NULL);
        }
        return products;
    }

    private Map<String,Object> updateProducts(Long id, LoginUserModel model, Products products) {

        Map<String, Object> map = new HashMap<>();
        if(id == null) {
            map.put(Tips.REPLY_CODE, Tips.PARAMS_ERROR);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.PARAMS_ERROR));
            return map;

        }
        if(StringUtils.isEmpty(products.getUpdateReason())) {
            map.put(Tips.REPLY_CODE, Tips.PRODUCTS_UPDATEREASON_NULL);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.PRODUCTS_UPDATEREASON_NULL));
            return map;
        }

        Products oldProducts = productsMapper.getProductsByField("id", id);

        if(oldProducts == null) {
            map.put(Tips.REPLY_CODE, Tips.PRODUCTS_NOT_EXIST);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.PRODUCTS_NOT_EXIST));
            return map;
        }

        try {
            products = OperateBeanUtil.setPackBean(products, oldProducts);
        } catch (Exception e) {
            e.printStackTrace();
            map.put(Tips.REPLY_CODE, Tips.PRODUCTS_UPDATE_ERROR);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.PARTNER_UPDATE_ERROR));
            return  map;
        }
        try {
            this.validParams(products);
        } catch (ProductsException e) {
            map.put(Tips.REPLY_CODE, Long.valueOf(e.getMessage()));
            map.put(Tips.REPLY_MSG, Tips.getTip(Long.valueOf(e.getMessage())));
            return  map;
        }

        productsMapper.updateProducts(id, products);
        map.put(Tips.REPLY_CODE, Tips.ACTION_SUCCESS);
        map.put(Tips.REPLY_MSG, Tips.getTip(Tips.ACTION_SUCCESS));
        map.put("products", products);

        return map;
    }


}
