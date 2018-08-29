package com.service.login.impl;

import com.alibaba.druid.util.StringUtils;
import com.bean.user.User;
import com.mapper.UserMapper;
import com.model.LoginUserModel;
import com.service.login.LoginService;
import com.service.user.UserService;
import com.utils.MD5;
import com.utils.Tips;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
@ComponentScan("com.mapper")
@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Resource
    UserMapper userMapper;

    @Override
    public Map<String, Object> login(String userName, String password) {
        Map<String, Object> map = new HashMap<>();
        if(StringUtils.isEmpty(userName)) {
            map.put(Tips.REPLY_CODE, Tips.LOGIN_USERNAME_NULL);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.LOGIN_USERNAME_NULL));
            return map;
        }
        if(StringUtils.isEmpty(password)) {
            map.put(Tips.REPLY_CODE, Tips.LOGIN_PASSWORD_NULL);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.LOGIN_PASSWORD_NULL));
            return map;
        }
        User user = userMapper.getUserByField("userName", userName);
        if(user == null) {
            map.put(Tips.REPLY_CODE, Tips.USER_NOT_EXIST);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.USER_NOT_EXIST));
            return map;
        }
        if (!MD5.TMD5(password).equals(user.getPassword())) {
//            logger.debug("password error with username = " + username);
            map.put(Tips.REPLY_CODE, Tips.LOGIN_PASSWORD_ERROR);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.LOGIN_PASSWORD_ERROR));
            return map;
        }
        LoginUserModel loginUserModel = new LoginUserModel();
        loginUserModel.setUser(user);
        map.put("model", loginUserModel);
        map.put(Tips.REPLY_CODE, Tips.ACTION_SUCCESS);
        map.put(Tips.REPLY_MSG, Tips.getTip(Tips.ACTION_SUCCESS));
        return map;
    }
}
