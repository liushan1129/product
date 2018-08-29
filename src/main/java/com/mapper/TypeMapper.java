package com.mapper;

import com.bean.type.Stype;
import com.bean.type.TypeParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface TypeMapper {

    List<Stype> findTypeListByParams(TypeParams typeParams);

    void addType(Stype type);

    Stype getTypeByField(@Param("filedName") String filedName, @Param("filedValue") Object filedValue);


    void updateType(@Param("id") Long id, @Param("type") Stype type);

//    void deleteMaterial(@Param("id") Long id);
}
