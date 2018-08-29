package com.service.user.impl;

import com.alibaba.druid.util.StringUtils;
import com.bean.user.User;
import com.bean.user.UserParams;
import com.exception.UserException;
import com.github.pagehelper.PageHelper;
import com.mapper.UserMapper;
import com.model.LoginUserModel;
import com.service.common.CommonService;
import com.service.user.UserService;
import com.utils.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ComponentScan("com.mapper")
@Service("userService")
public class UserServiceImpl implements UserService {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    @Resource
    public UserMapper userMapper;
    @Resource
    public CommonService commonService;

    @Override
    public Map<String, Object> getUserList(LoginUserModel model, UserParams userParams, Long pageNum, Long pageSize) {

        Map<String, Object> map = new HashMap<>();
//        if(model == null) {
//            map.put(Tips.REPLY_CODE, Tips.NO_LOGIN);
//            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.NO_LOGIN));
//            return map;
//        }
//        User loginUser = model.getUser();
        if(pageNum == null ) {
            pageNum = 1L;
        }
        if(pageSize == null) {
            pageSize = 5L;
        }
        //mapper分页
        PageHelper.startPage(Integer.parseInt(pageNum.toString()), Integer.parseInt(pageSize.toString()), true);
        List<User> userList = userMapper.findUserListByParams(userParams);

        Map<Long, User> userMap = commonService.getUserMap();
        //调整数据
        for(User user : userList) {

            user.setShowTime(sdf.format(user.getAddTime()));
            user.setAddUserName(userMap.get(user.getAddUserId()) == null ? null : userMap.get(user.getAddUserId()).getUserName());

        }
        PageInfo<User> userPageInfo = new PageInfo(userList);

        map.put("userPageInfo", userPageInfo);
        map.put(Tips.REPLY_CODE, Tips.ACTION_SUCCESS);
        map.put(Tips.REPLY_MSG, Tips.getTip(Tips.ACTION_SUCCESS));

        return map;
    }

    @Override
    public Map<String, Object> saveOrUpdateUser(Long id, User user, LoginUserModel model) {
        Map<String, Object> map = new HashMap<>();
        if (model == null) {
            map.put(Tips.REPLY_CODE, Tips.NO_LOGIN);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.NO_LOGIN));
            return map;

        }
        User loginUser = model.getUser();


        if(id != null) {
            map = this.updateUser(id, user, loginUser);
        } else {
            map = this.saveUser(user, loginUser);
        }
        return map ;
    }

    private Map<String,Object> saveUser(User user, User loginUser) {
        //判断用户是否有添加用户的权限
        //TODO

        Map<String, Object> map = new HashMap<>();
        //验证必填参数
        try{
            user = this.validParams(user);

        } catch (UserException e) {
            e.printStackTrace();
            map.put(Tips.REPLY_CODE, Long.valueOf(e.getMessage()));
            map.put(Tips.REPLY_MSG, Tips.getTip(Long.valueOf(e.getMessage())));
            return  map;
        }
        //验证参数，根据用户名和手机号判断用户是否重复
        User exitUser = userMapper.findUserByNameAndMobile(user.getUserName(), user.getMobile());
        if (exitUser != null) {
            map.put(Tips.REPLY_CODE, Tips.USER_EXIST);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.USER_EXIST));
            return map;
        }
        Date nowData = new Date();
        user.setAddTime(nowData);
        //添加人設置為當前登錄用戶的id
        user.setAddUserId(loginUser.getId());
        userMapper.addUser(user);

        map.put("user", user);
        map.put(Tips.REPLY_CODE, Tips.ACTION_SUCCESS);
        map.put(Tips.REPLY_MSG, Tips.getTip(Tips.ACTION_SUCCESS));

        return map;
    }

    public Map<String, Object> updateUser(Long id, User user, User loginUser) {

        Map<String, Object> map = new HashMap<>();

        if(id == null) {
            map.put(Tips.REPLY_CODE, Tips.PARAMS_ERROR);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.PARAMS_ERROR));
            return map;

        }
        User oldUser = userMapper.getUserByField("id", id);

        if(oldUser == null) {
            map.put(Tips.REPLY_CODE, Tips.USER_NOT_EXIST);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.USER_NOT_EXIST));
            return map;
        }

        try {
            user = OperateBeanUtil.setPackBean(user, oldUser);
        } catch (Exception e) {
            map.put(Tips.REPLY_CODE, Tips.USER_UPDATE_ERROR);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.USER_UPDATE_ERROR));
            return  map;
        }
        try {
            this.validParams(user);
        } catch (UserException e) {
            map.put(Tips.REPLY_CODE, Long.valueOf(e.getMessage()));
            map.put(Tips.REPLY_MSG, Tips.getTip(Long.valueOf(e.getMessage())));
            return  map;
        }
        //验证参数，根据用户名和手机号判断用户是否重复
        User exitUser = userMapper.findUserByNameAndMobile(user.getUserName(), user.getMobile());
        if (exitUser != null) {
            map.put(Tips.REPLY_CODE, Tips.USER_EXIST);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.USER_EXIST));
            return map;
        }

        userMapper.updateUser(id, user);
        map.put(Tips.REPLY_CODE, Tips.ACTION_SUCCESS);
        map.put(Tips.REPLY_MSG, Tips.getTip(Tips.ACTION_SUCCESS));
        map.put("user", user);

        return map;
    }

//    @Override
//    public User getUserByField(String filedName, Object filedValue) {
//
//        User user = userMapper.getUserByField(filedName, filedValue);
//        return user;
//    }

    @Override
    public Map<String, Object> deleteUser(Long id, LoginUserModel model) {

        Map<String, Object> map = new HashMap<>();

        //根据id查询用户是否存在
        User user = userMapper.getUserByField("id", id);
        if(user == null) {

            map.put(Tips.REPLY_CODE, Tips.USER_NOT_EXIST);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.USER_NOT_EXIST));
            return map;

        }
        userMapper.deleteUser(id);

        map.put(Tips.REPLY_CODE, Tips.ACTION_SUCCESS);
        map.put(Tips.REPLY_MSG, Tips.getTip(Tips.ACTION_SUCCESS));

        return map;
    }

    private User validParams(User user) throws UserException {
        // 验证对象是否为空
        if (user == null) {
//            logger.debug(lm + " brand is null");
            return user;
        }
        //获取用户名
        String userName = user.getUserName();
        if(StringUtils.isEmpty(userName) || userName == null || userName == "") {
            throw new UserException(Tips.USER_NAME_NULL);
        }
        //获取密码
        String password = user.getPassword();
        if(StringUtils.isEmpty(password) || password == null || password == "") {
            throw new UserException(Tips.USER_PASSWORD_NULL);
        }
        //获取联系方式
        String mobile = user.getMobile();
        if(StringUtils.isEmpty(mobile) || mobile == null || mobile == "") {
            throw new UserException(Tips.USER_MOBILE_NULL);
        }
        //获取地址
        String address = user.getAddress();
        if(StringUtils.isEmpty(address) || address == null || address == "") {
            throw new UserException(Tips.USER_ADDRESS_NULL);
        }
        //获取权限
        String realmId = user.getRealmId();
        if(StringUtils.isEmpty(realmId) || realmId == null || realmId == "") {
            throw new UserException(Tips.USER_REALM_NULL);
        }

        //判断用户密码是否为空，不为空则对密码进行加密
        if(!StringUtils.isEmpty(password) || password != null || password != "") {
           password = MD5.TMD5(password);
            user.setPassword(password);
        }

        return user;
    }



}
