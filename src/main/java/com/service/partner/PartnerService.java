package com.service.partner;

import com.bean.partner.Partner;
import com.bean.partner.PartnerParams;
import com.model.LoginUserModel;

import java.util.Map;

public interface PartnerService {
    /**
     * 保存、修改原料提供商
     * @param id
     * @param partner
     * @param model
     * @return
     */
    Map<String,Object> saveOrUpdatePartner(Long id, Partner partner, LoginUserModel model);


    /**
     * 根据查询条件分页查询厂商信息
     * @param model
     * @param partnerParams
     * @param pageNum
     * @param pageSize
     * @return
     */
    Map<String,Object> getPartnerList(LoginUserModel model, PartnerParams partnerParams, Long pageNum, Long pageSize);

    /**
     * 删除厂商信息
     * @param id
     * @param model
     * @return
     */
    Map<String,Object> deletePartner(Long id, LoginUserModel model);
}
