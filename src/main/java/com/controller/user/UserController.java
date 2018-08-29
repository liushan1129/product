package com.controller.user;

import com.bean.user.User;
import com.bean.user.UserParams;
import com.model.LoginUserModel;
import com.service.user.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@ComponentScan({"com.service"})
@MapperScan("com.mapper")
public class UserController {

    @Resource
    public UserService userService;

    /**
     * 获取用户列表
     * @return
     */
    @RequestMapping("/user/getUserList")
    public Map<String, Object> findUserList(UserParams userParams, Long pageNum, Long pageSize, HttpSession session) {

        LoginUserModel model = (LoginUserModel) session.getAttribute("model");
        Map<String, Object> map = userService.getUserList(model, userParams, pageNum, pageSize);

        return map;

    }

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    @RequestMapping("/user/saveOrUpdateUser")
    public Map<String, Object> saveOrUpdateUser(Long id, User user, HttpSession session) {
        LoginUserModel model = (LoginUserModel) session.getAttribute("model");

        Map<String, Object> map = userService.saveOrUpdateUser(id, user, model);


        return map;

    }

    /**
     * 删除用户信息
     * @param user
     * @return
     */
    @RequestMapping("/user/deleteUser")
    public Map<String, Object> deleteUser(Long id, User user, HttpSession session) {
        LoginUserModel model = (LoginUserModel) session.getAttribute("model");

        Map<String, Object> map = userService.deleteUser(id, model);

        return map;

    }


}
