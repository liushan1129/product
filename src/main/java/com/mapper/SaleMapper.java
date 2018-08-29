package com.mapper;

import com.bean.products.Products;
import com.bean.products.ProductsParams;
import com.bean.sale.Sale;
import com.bean.sale.SaleParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface SaleMapper {

    List<Sale> findSaleListByParams(SaleParams saleParams);

    void addSale(Sale sale);

    Sale getSaleByField(@Param("filedName") String filedName, @Param("filedValue") Object filedValue);


    void updateSale(@Param("id") Long id, @Param("sale") Sale sale);

//    void deleteMaterial(@Param("id") Long id);
}
