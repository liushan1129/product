package com.service.sale;

import com.bean.sale.Sale;
import com.bean.sale.SaleParams;
import com.model.LoginUserModel;

import java.util.Map;

public interface SaleService {
    Map<String,Object> saveOrUpdateSale(Long id, Sale sale, LoginUserModel model);

    Map<String,Object> getSaleList(LoginUserModel model, SaleParams saleParams, Long pageNum, Long pageSize);
}
