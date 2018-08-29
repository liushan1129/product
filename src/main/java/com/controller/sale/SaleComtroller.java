package com.controller.sale;


import com.bean.products.Products;
import com.bean.products.ProductsParams;
import com.bean.sale.Sale;
import com.bean.sale.SaleParams;
import com.model.LoginUserModel;
import com.service.sale.SaleService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@ComponentScan({"com.service"})
@MapperScan("com.mapper")
public class SaleComtroller {

    @Resource
    public SaleService saleService;
    /**
     * 保存用户信息
     * @param
     * @return
     */
    @RequestMapping("/sale/saveOrUpdateSale")
    public Map<String, Object> saveOrUpdateSale(Long id, Sale sale, HttpSession session) {
        LoginUserModel model = (LoginUserModel) session.getAttribute("model");

        Map<String, Object> map = saleService.saveOrUpdateSale(id, sale, model);

        return map;

    }

    @RequestMapping("/sale/getSaleList")
    public Map<String, Object> getSaleList(SaleParams saleParams, Long pageNum, Long pageSize, HttpSession session) {

        LoginUserModel model = (LoginUserModel) session.getAttribute("model");

        Map<String, Object> map = saleService.getSaleList(model, saleParams, pageNum, pageSize);

        return map;

    }
}
