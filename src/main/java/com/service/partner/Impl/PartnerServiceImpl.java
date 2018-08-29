package com.service.partner.Impl;

import com.alibaba.druid.util.StringUtils;
import com.bean.partner.Partner;
import com.bean.partner.PartnerParams;
import com.bean.user.User;
import com.exception.PartnerException;
import com.github.pagehelper.PageHelper;
import com.mapper.PartnerMapper;
import com.model.LoginUserModel;
import com.service.partner.PartnerService;
import com.utils.OperateBeanUtil;
import com.utils.PageInfo;
import com.utils.Tips;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ComponentScan("com.mapper")
@Service("partnerService")
public class PartnerServiceImpl implements PartnerService {

    @Resource
    public PartnerMapper partnerMapper;

    @Override
    public Map<String, Object> saveOrUpdatePartner(Long id, Partner partner, LoginUserModel model) {

        Map<String, Object> map = new HashMap<>();
        if(model == null) {
            map.put(Tips.REPLY_CODE, Tips.NO_LOGIN);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.NO_LOGIN));
            return map;
        }
        User loginUser = model.getUser();

        if(id != null) {
            map = this.updatePartner(id, partner, loginUser);
        } else {
            map = this.savePartner(partner, loginUser);
        }

        return map;
    }


    private Map<String,Object> savePartner(Partner partner, User loginUser) {
        Map<String, Object> map = new HashMap<>();

        //验证必填参数
        try{
            partner = this.validParams(partner);

        } catch (PartnerException e) {
            map.put(Tips.REPLY_CODE, Long.valueOf(e.getMessage()));
            map.put(Tips.REPLY_MSG, Tips.getTip(Long.valueOf(e.getMessage())));
            return  map;
        }
        //根据原料商名称查询判断是否已存在
        Partner existPartner = partnerMapper.getPartnerByField("pName", partner.getPName());
        if(existPartner != null) {
            map.put(Tips.REPLY_CODE, Tips.PARTNER_EXIST);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.PARTNER_EXIST));
            return map;
        }
        Timestamp nowData = new Timestamp(new Date().getTime());
        partner.setAddTime(nowData);
        //添加人設置為當前登錄用戶的id
        partner.setAddUserId(loginUser.getId());
        partnerMapper.addPartner(partner);

        map.put("partner", partner);
        map.put(Tips.REPLY_CODE, Tips.ACTION_SUCCESS);
        map.put(Tips.REPLY_MSG, Tips.getTip(Tips.ACTION_SUCCESS));
        return map;
    }



    private Map<String,Object> updatePartner(Long id, Partner partner, User loginUser) {
        Map<String, Object> map = new HashMap<>();
        if(id == null) {
            map.put(Tips.REPLY_CODE, Tips.PARAMS_ERROR);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.PARAMS_ERROR));
            return map;

        }
        Partner oldPartner = partnerMapper.getPartnerByField("id", id);

        if(oldPartner == null) {
            map.put(Tips.REPLY_CODE, Tips.PARTNER_NOT_EXIST);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.PARTNER_NOT_EXIST));
            return map;
        }

        try {
            partner = OperateBeanUtil.setPackBean(partner, oldPartner);
        } catch (Exception e) {
            e.printStackTrace();
            map.put(Tips.REPLY_CODE, Tips.PARTNER_UPDATE_ERROR);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.PARTNER_UPDATE_ERROR));
            return  map;
        }
        try {
            this.validParams(partner);
        } catch (PartnerException e) {
            map.put(Tips.REPLY_CODE, Long.valueOf(e.getMessage()));
            map.put(Tips.REPLY_MSG, Tips.getTip(Long.valueOf(e.getMessage())));
            return  map;
        }
        //验证参数，根据用户名和手机号判断用户是否重复
        Partner exitPartner = partnerMapper.getPartnerByField("pName", partner.getPName());
        if (exitPartner != null) {
            map.put(Tips.REPLY_CODE, Tips.PARTNER_EXIST);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.PARTNER_EXIST));
            return map;
        }

        partnerMapper.updatePartner(id, partner);
        map.put(Tips.REPLY_CODE, Tips.ACTION_SUCCESS);
        map.put(Tips.REPLY_MSG, Tips.getTip(Tips.ACTION_SUCCESS));
        map.put("partner", partner);

        return map;
    }

    private Partner validParams(Partner partner) throws PartnerException {
        // 验证对象是否为空
        if (partner == null) {
//            logger.debug(lm + " brand is null");
            return partner;
        }
        //获取商户名
        String pName = partner.getPName();
        if(StringUtils.isEmpty(pName) || pName == null || pName == "") {
            throw new PartnerException(Tips.PARTNER_PNAME_NULL);
        }
        //获取用户名
        String userName = partner.getUserName();
        if(StringUtils.isEmpty(userName) || userName == null || userName == "") {
            throw new PartnerException(Tips.PARTNER_USERNAME_NULL);
        }
        //获取联系人电话
        String mobile = partner.getMobile();
        if(StringUtils.isEmpty(mobile) || mobile == null || mobile == "") {
            throw new PartnerException(Tips.PARTNER_MOBILE_NULL);
        }
        //获取地址
        String address = partner.getAddress();
        if(StringUtils.isEmpty(address) || address == null || address == "") {
            throw new PartnerException(Tips.PARTNER_ADDRESS_NULL);
        }
        //获取标识
        Integer mark = partner.getMark();
        if(mark == null) {
            throw new PartnerException(Tips.PARTNER_MARK_NULL);
        }
        return partner;
    }

    @Override
    public Map<String, Object> getPartnerList(LoginUserModel model, PartnerParams partnerParams, Long pageNum, Long pageSize) {
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
        List<Partner> partnerList = partnerMapper.findPartnerListByParams(partnerParams);
        PageInfo<Partner> partnerPageInfo = new PageInfo(partnerList);

        map.put("partnerPageInfo", partnerPageInfo);
        map.put(Tips.REPLY_CODE, Tips.ACTION_SUCCESS);
        map.put(Tips.REPLY_MSG, Tips.getTip(Tips.ACTION_SUCCESS));

        return map;
    }

    @Override
    public Map<String, Object> deletePartner(Long id, LoginUserModel model) {
        Map<String, Object> map = new HashMap<>();

        //根据id查询用户是否存在
        Partner partner = partnerMapper.getPartnerByField("id", id);
        if(partner == null) {

            map.put(Tips.REPLY_CODE, Tips.PARTNER_NOT_EXIST);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.PARTNER_NOT_EXIST));
            return map;

        }
        partnerMapper.deletePartner(id);

        map.put(Tips.REPLY_CODE, Tips.ACTION_SUCCESS);
        map.put(Tips.REPLY_MSG, Tips.getTip(Tips.ACTION_SUCCESS));

        return map;
    }

}
