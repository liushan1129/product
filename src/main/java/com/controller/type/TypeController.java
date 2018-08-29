package com.controller.type;

import com.bean.type.Stype;
import com.bean.type.TypeParams;
import com.model.LoginUserModel;
import com.service.type.TypeService;
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
public class TypeController {

    @Resource
    public TypeService typeService;

    /**
     * 保存用户信息
     * @param
     * @return
     */
    @RequestMapping("/type/saveOrUpdateType")
    public Map<String, Object> saveOrUpdateType(Long id, Stype type, HttpSession session) {
        LoginUserModel model = (LoginUserModel) session.getAttribute("model");

        Map<String, Object> map = typeService.saveOrUpdateType(id, type, model);


        return map;

    }

    @RequestMapping("/type/getList")
    public Map<String, Object> findTypeList(TypeParams typeParams, Long pageNum, Long pageSize, HttpSession session) {

        LoginUserModel model = (LoginUserModel) session.getAttribute("model");

        Map<String, Object> map = typeService.getTypeList(model, typeParams, pageNum, pageSize);

        return map;

    }
}
