package com.service.type;

import com.bean.type.Stype;
import com.bean.type.TypeParams;
import com.model.LoginUserModel;

import java.util.Map;

public interface TypeService {
    /**
     * 保存/修改类型
     * @param id
     * @param type
     * @param model
     * @return
     */
    Map<String,Object> saveOrUpdateType(Long id, Stype type, LoginUserModel model);


    /**
     * 查询类型列表
     * @param model
     * @param typeParams
     * @param pageNum
     * @param pageSize
     * @return
     */
    Map<String,Object> getTypeList(LoginUserModel model, TypeParams typeParams, Long pageNum, Long pageSize);
}
