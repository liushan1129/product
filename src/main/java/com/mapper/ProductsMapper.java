package com.mapper;

import com.bean.products.Products;
import com.bean.products.ProductsParams;
import com.bean.type.Stype;
import com.bean.type.TypeParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface ProductsMapper {

    List<Products> findProductsListByParams(ProductsParams productsParams);

    void addProducts(Products products);

    Products getProductsByField(@Param("filedName") String filedName, @Param("filedValue") Object filedValue);


    void updateProducts(@Param("id") Long id, @Param("products") Products products);

//    void deleteMaterial(@Param("id") Long id);
}
