package com.controller.products;

import com.bean.products.Products;
import com.bean.products.ProductsParams;
import com.model.LoginUserModel;
import com.service.products.ProductsService;
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
public class ProductsController {


    @Resource
    public ProductsService productsService;
    /**
     * 保存用户信息
     * @param
     * @return
     */
    @RequestMapping("/products/saveOrUpdateProducts")
    public Map<String, Object> saveOrUpdateProducts(Long id, Products products, HttpSession session) {
        LoginUserModel model = (LoginUserModel) session.getAttribute("model");

        Map<String, Object> map = productsService.saveOrUpdateProducts(id, products, model);

        return map;

    }

    @RequestMapping("/products/getProductsList")
    public Map<String, Object> findProductsList(ProductsParams productsParams, Long pageNum, Long pageSize, HttpSession session) {

        LoginUserModel model = (LoginUserModel) session.getAttribute("model");

        Map<String, Object> map = productsService.getProductsList(model, productsParams, pageNum, pageSize);

        return map;

    }

}
