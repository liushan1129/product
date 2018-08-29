package com.controller.login;

import com.model.LoginUserModel;
import com.service.login.LoginService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@ComponentScan({"com.service"})
@MapperScan("com.mapper")
public class LoginController {


    @Autowired
    @Qualifier("loginService")
    LoginService loginService;

    @RequestMapping("/login")
    public Map<String, Object> userLogin(String userName, String password, HttpSession session) {

        Map<String, Object> map = new HashMap<>();
        map = loginService.login(userName, password);

        //获取登录成功后的用户封装信息，放入session中,供过滤器使用
        LoginUserModel model = (LoginUserModel) map.get("model");
        session.setAttribute("model", model);
        session.setAttribute("map", map);

        return map;
    }
}
