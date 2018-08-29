package com.service.type.impl;

import com.alibaba.druid.util.StringUtils;
import com.bean.type.Stype;
import com.bean.type.TypeParams;
import com.bean.user.User;
import com.exception.TypeException;
import com.github.pagehelper.PageHelper;
import com.mapper.TypeMapper;
import com.model.LoginUserModel;
import com.service.type.TypeService;
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

@Service("typeService")
public class TypeServiceImpl implements TypeService {

    @Resource
    public TypeMapper typeMapper;

    @Override
    public Map<String, Object> saveOrUpdateType(Long id, Stype type, LoginUserModel model) {
        Map<String, Object> map = new HashMap<>();
        if(model == null) {
            map.put(Tips.REPLY_CODE, Tips.NO_LOGIN);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.NO_LOGIN));
            return map;

        }
        if(id == null) {
            map = this.saveType(model, type);
        }else {
            map = this.updateType(id, model, type);
        }

        return map;
    }

    private Map<String,Object> saveType(LoginUserModel model, Stype type) {
        Map<String, Object> map = new HashMap<>();
        User loginUser = model.getUser();
        //判断当前登录用户是否有权限
        //TODO


        //验证参数
        try {
            type = this.validParams(type);
        } catch (TypeException e) {
            e.printStackTrace();
            map.put(Tips.REPLY_CODE, Long.valueOf(e.getMessage()));
            map.put(Tips.REPLY_MSG, Tips.getTip(Long.valueOf(e.getMessage())));
            return  map;
        }
        //根据名称查询是否已存在
        Stype existType = typeMapper.getTypeByField("name", type.getName());

        if(existType != null) {
            map.put(Tips.REPLY_CODE, Tips.TYPE_NAME_EXIST);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.TYPE_NAME_EXIST));
            return map;
        }

        Timestamp nowData = new Timestamp(new Date().getTime());
        type.setAddTime(nowData);
        //添加人設置為當前登錄用戶的id
        type.setAddUserId(loginUser.getId());
        typeMapper.addType(type);

        map.put("type", type);
        map.put(Tips.REPLY_CODE, Tips.ACTION_SUCCESS);
        map.put(Tips.REPLY_MSG, Tips.getTip(Tips.ACTION_SUCCESS));


        return map;
    }

    private Stype validParams(Stype type) throws TypeException {
        if(type == null) {
            return type;
        }
        //获取类型名称
        String name = type.getName();
        if(name == null || StringUtils.isEmpty(name) || name == "") {
            throw new TypeException(Tips.TYPE_NAME_NULL);
        }

        return type;
    }

    private Map<String,Object> updateType(Long id, LoginUserModel model, Stype type) {
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
        Stype oldType= typeMapper.getTypeByField("id", id);
        if(oldType == null) {
            map.put(Tips.REPLY_CODE, Tips.TYPE_NOTEXIST);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.TYPE_NOTEXIST));
            return map;
        }
        try {
            type = OperateBeanUtil.setPackBean(type, oldType);
        } catch (Exception e) {
            e.printStackTrace();
            map.put(Tips.REPLY_CODE, Tips.TYPE_UPDATE_ERROR);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.TYPE_UPDATE_ERROR));
            return  map;
        }
        try {
            this.validParams(type);
        } catch (TypeException e) {
            map.put(Tips.REPLY_CODE, Long.valueOf(e.getMessage()));
            map.put(Tips.REPLY_MSG, Tips.getTip(Long.valueOf(e.getMessage())));
            return  map;
        }

        typeMapper.updateType(id, type);
        map.put(Tips.REPLY_CODE, Tips.ACTION_SUCCESS);
        map.put(Tips.REPLY_MSG, Tips.getTip(Tips.ACTION_SUCCESS));
        map.put("type", type);
        return map;
    }


    @Override
    public Map<String, Object> getTypeList(LoginUserModel model, TypeParams typeParams, Long pageNum, Long pageSize) {
        Map<String, Object> map = new HashMap<>();
//        if(model == null) {
//            map.put(Tips.REPLY_CODE, Tips.NO_LOGIN);
//            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.NO_LOGIN));
//            return map;
//        }
//        User loginUser = model.getUser();
        if (pageNum == null) {
            pageNum = 1L;
        }
        if (pageSize == null) {
            pageSize = 5L;
        }
        //mapper分页
        PageHelper.startPage(Integer.parseInt(pageNum.toString()), Integer.parseInt(pageSize.toString()), true);
        List<Stype> typeList = typeMapper.findTypeListByParams(typeParams);
        PageInfo<Stype> typePageInfo = new PageInfo(typeList);

        map.put("typePageInfo", typePageInfo);
        map.put(Tips.REPLY_CODE, Tips.ACTION_SUCCESS);
        map.put(Tips.REPLY_MSG, Tips.getTip(Tips.ACTION_SUCCESS));

        return map;
    }
}
