package com.controller.partner;

import com.bean.partner.Partner;
import com.bean.partner.PartnerParams;
import com.model.LoginUserModel;
import com.service.partner.PartnerService;
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
public class PartnerController {

    @Resource
    public PartnerService partnerService;

    /**
     * 保存用户信息
     * @param
     * @return
     */
    @RequestMapping("/partner/saveOrUpdatePartner")
    public Map<String, Object> saveOrUpdatePartner(Long id, Partner partner, HttpSession session) {
        LoginUserModel model = (LoginUserModel) session.getAttribute("model");

        Map<String, Object> map = partnerService.saveOrUpdatePartner(id, partner, model);


        return map;

    }
    @RequestMapping("/partner/getPartnerList")
    public Map<String, Object> findPartnerList(PartnerParams partnerParams, Long pageNum, Long pageSize, HttpSession session) {

        LoginUserModel model = (LoginUserModel) session.getAttribute("model");

        Map<String, Object> map = partnerService.getPartnerList(model, partnerParams, pageNum, pageSize);

        return map;

    }


    @RequestMapping("/partner/deletePartner")
    public Map<String, Object> deletePartner(Long id, Partner partner, HttpSession session) {
        LoginUserModel model = (LoginUserModel) session.getAttribute("model");

        Map<String, Object> map = partnerService.deletePartner(id, model);


        return map;

    }

}
