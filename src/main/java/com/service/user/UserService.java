package com.service.user;

import com.bean.user.User;
import com.bean.user.UserParams;
import com.model.LoginUserModel;

import java.util.Map;

public interface UserService {

    /**
     *
     *
     * @param model
     * @param userParams
     * @param pageNum
     * @param pageSize
     * @return
     */
    Map<String, Object> getUserList(LoginUserModel model, UserParams userParams, Long pageNum, Long pageSize);
    Map<String, Object> saveOrUpdateUser(Long id, User user, LoginUserModel model);

//    User getUserByField(String filedName, Object filedValue);

    Map<String,Object> deleteUser(Long id, LoginUserModel model);

//    Map<String,Object> updateUser(Long id, User user, User loginUser);
}
