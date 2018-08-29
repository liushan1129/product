package com.service.common.impl;

import com.bean.user.User;
import com.mapper.UserMapper;
import com.service.common.CommonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("commonService")
public class CommonServiceImpl implements CommonService {

    @Resource
    public UserMapper userMapper;

    @Override
    public Map<Long, User> getUserMap() {
        Map<Long, User> map = new HashMap<>();
        List<User> userList = userMapper.findUserList();
        for(User user : userList) {
            map.put(user.getId(), user);
        }
        return map;
    }
}
