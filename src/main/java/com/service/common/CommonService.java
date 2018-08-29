package com.service.common;

import com.bean.user.User;

import java.util.Map;

public interface CommonService {

    //获取用户map
    Map<Long, User> getUserMap();
}
