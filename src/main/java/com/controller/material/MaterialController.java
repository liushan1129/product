package com.controller.material;

import com.bean.material.Material;
import com.bean.material.MaterialParams;
import com.bean.partner.PartnerParams;
import com.model.LoginUserModel;
import com.service.material.MaterialService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 原料操作（进布记录）
 */
@RestController
@ComponentScan({"com.service"})
@MapperScan("com.mapper")
public class MaterialController {

    @Resource
    public MaterialService materialService;

    //增加原料信息
    @RequestMapping("/material/saveOrUpdateMaterial")
    public Map<String, Object> addMaterial(Long id, Material material, HttpSession session) {

        LoginUserModel model = (LoginUserModel) session.getAttribute("model");
        Map<String, Object> map = materialService.saveOrUpdateMaterial(id, model, material);
        return map;

    }
    //增加原料信息
    @RequestMapping("/material/getList")
    public Map<String, Object> getMaterialList(MaterialParams materialParams, Long pageNum, Long pageSize, HttpSession session) {

        LoginUserModel model = (LoginUserModel) session.getAttribute("model");
        Map<String, Object> map = materialService.getMateriaList(materialParams, pageNum, pageSize, model);
        return map;

    }
}
