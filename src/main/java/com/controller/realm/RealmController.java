package com.controller.realm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限操作
 */

@RestController
@ComponentScan({"com.service"})
@MapperScan("com.mapper")
public class RealmController {

    //添加权限

}
