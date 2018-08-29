package com.service.material.impl;

import com.alibaba.druid.util.StringUtils;
import com.bean.material.Material;
import com.bean.material.MaterialParams;
import com.bean.partner.Partner;
import com.bean.user.User;
import com.exception.MaterialException;
import com.exception.UserException;
import com.github.pagehelper.PageHelper;
import com.mapper.MaterialMapper;
import com.mapper.PartnerMapper;
import com.model.LoginUserModel;
import com.service.material.MaterialService;
import com.utils.OperateBeanUtil;
import com.utils.PageInfo;
import com.utils.Tips;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("materialService")
public class MaterialServiceImpl implements MaterialService {

    @Resource
    public MaterialMapper materialMapper;
    @Resource
    public PartnerMapper partnerMapper;

    @Override
    public Map<String, Object> saveOrUpdateMaterial(Long id, LoginUserModel model, Material material) {
        Map<String, Object> map = new HashMap<>();
        if(model == null) {
            map.put(Tips.REPLY_CODE, Tips.NO_LOGIN);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.NO_LOGIN));
            return map;
            
        }
        if(id == null) {
            map = this.saveMaterial(model, material);
        }else {
            map = this.updateMaterial(id, model, material);
        }
        
        return map;
    }

    @Override
    public Map<String, Object> getMateriaList(MaterialParams materialParams, Long pageNum, Long pageSize, LoginUserModel model) {
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
        List<Material> materialList = materialMapper.findMaterialListByParams(materialParams);
        PageInfo<Material> materialPageInfo = new PageInfo(materialList);

        map.put("materialPageInfo", materialPageInfo);
        map.put(Tips.REPLY_CODE, Tips.ACTION_SUCCESS);
        map.put(Tips.REPLY_MSG, Tips.getTip(Tips.ACTION_SUCCESS));

        return map;
    }

    private Map<String,Object> saveMaterial(LoginUserModel model, Material material) {
        Map<String, Object> map = new HashMap<>();
        User loginUser = model.getUser();
        //判断当前登录用户是否有权限
        //TODO


        //验证参数
        try {
            material = this.validParams(material);
        } catch (MaterialException e) {
            e.printStackTrace();
            map.put(Tips.REPLY_CODE, Long.valueOf(e.getMessage()));
            map.put(Tips.REPLY_MSG, Tips.getTip(Long.valueOf(e.getMessage())));
            return  map;
        }
        Timestamp nowData = new Timestamp(new Date().getTime());
        material.setAddTime(nowData);
        //添加人設置為當前登錄用戶的id
        material.setAddUserId(loginUser.getId());
        materialMapper.addMaterial(material);

        map.put("material", material);
        map.put(Tips.REPLY_CODE, Tips.ACTION_SUCCESS);
        map.put(Tips.REPLY_MSG, Tips.getTip(Tips.ACTION_SUCCESS));

        return map;

    }

    //验证参数
    private Material validParams(Material material) throws MaterialException {
        if(material == null) {
            return material;
        }

        Timestamp nowTime = new Timestamp(new Date().getTime());
        //获取厂商id
        Long partnerId = material.getPartnerId();
        if(partnerId == null) {
            throw new MaterialException(Tips.MATERIAL_PARTNERID_NULL);
        }
        //查询partner是否存在
        Partner partner = partnerMapper.getPartnerByField("id", partnerId);
        if(partner == null) {
            throw new MaterialException(Tips.PARTNER_NOT_EXIST);
        }
        //获取原材料名称
        String name = material.getName();
        if(StringUtils.isEmpty(name) || name == null || name == "") {
            throw new MaterialException(Tips.MATERIAL_NAME_NULL);
        }
        //获取进货时间
        Timestamp intoTime = material.getIntoTime();
        if(intoTime == null) {
            material.setIntoTime(nowTime);

        }
        //获取进货米数
        Integer totalMeter = material.getTotalMeter();
        if(totalMeter == null) {
            throw new MaterialException(Tips.MATERIAL_TOTALMETER_NULL);
        }
        //获取金额
        Double money = material.getMoney();
        if(money == null) {
            throw new MaterialException(Tips.MATERIAL_MONEY_NULL);
        }
        return material;
    }

    private Map<String,Object> updateMaterial(Long id, LoginUserModel model, Material material) {

        //获取当前登录用户
        User loginUser = model.getUser();
        //判断权限
        //TODO


        Map<String, Object> map = new HashMap<>();
        if(id == null) {
            map.put(Tips.REPLY_CODE, Tips.PARAMS_ERROR);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.PARAMS_ERROR));
            return map;
        }
        //根据id查询该信息是否存在
        Material oldMaterial = materialMapper.getMaterialByField("id", id);
        if(oldMaterial == null) {
            map.put(Tips.REPLY_CODE, Tips.MATERIAL_NOTEXIST);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.MATERIAL_NOTEXIST));
            return map;
        }
        try {
            material = OperateBeanUtil.setPackBean(material, oldMaterial);
        } catch (Exception e) {
            e.printStackTrace();
            map.put(Tips.REPLY_CODE, Tips.MATERIAL_UPDATE_ERROR);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.MATERIAL_UPDATE_ERROR));
            return  map;
        }
        try {
            this.validParams(material);
        } catch (MaterialException e) {
            map.put(Tips.REPLY_CODE, Long.valueOf(e.getMessage()));
            map.put(Tips.REPLY_MSG, Tips.getTip(Long.valueOf(e.getMessage())));
            return  map;
        }

        materialMapper.updateMaterial(id, material);
        map.put(Tips.REPLY_CODE, Tips.ACTION_SUCCESS);
        map.put(Tips.REPLY_MSG, Tips.getTip(Tips.ACTION_SUCCESS));
        map.put("material", material);

        return map;
    }


}
