package com.service.material;

import com.bean.material.Material;
import com.bean.material.MaterialParams;
import com.model.LoginUserModel;

import javax.security.auth.spi.LoginModule;
import java.util.Map;

public interface MaterialService {

    /**
     * 保存，修改
     * @param id
     * @param model
     * @param material
     * @return
     */
    Map<String,Object> saveOrUpdateMaterial(Long id, LoginUserModel model, Material material);

    /**
     * 查询原料列表
     * @param materialParams
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    Map<String,Object> getMateriaList(MaterialParams materialParams, Long pageNum, Long pageSize, LoginUserModel model);
}
