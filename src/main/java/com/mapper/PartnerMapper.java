package com.mapper;

import com.bean.partner.Partner;
import com.bean.partner.PartnerParams;
import com.bean.user.User;
import com.bean.user.UserParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface PartnerMapper {

    List<Partner> findPartnerListByParams(PartnerParams partnerParams);

    void addPartner(Partner partner);

    Partner getPartnerByField(@Param("filedName") String filedName, @Param("filedValue") Object filedValue);


    void updatePartner(@Param("id") Long id, @Param("partner") Partner partner);

    void deletePartner(@Param("id") Long id);
}
