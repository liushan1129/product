package com.mapper;

import com.bean.material.Material;
import com.bean.material.MaterialParams;
import com.bean.partner.Partner;
import com.bean.partner.PartnerParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface MaterialMapper {

    List<Material> findMaterialListByParams(MaterialParams materialParams);

    void addMaterial(Material material);

    Material getMaterialByField(@Param("filedName") String filedName, @Param("filedValue") Object filedValue);


    void updateMaterial(@Param("id") Long id, @Param("material") Material material);

//    void deleteMaterial(@Param("id") Long id);
}
