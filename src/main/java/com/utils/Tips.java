package com.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 警告/错误信息整合类
 */
public class Tips {

    public final static String REPLY_CODE = "reply_code";
    public final static String REPLY_MSG = "reply_msg";
    public final static String REPLY_URL = "reply_url";
    public final static String REPLY_TIME = "reply_time";

    //原料供应商
    public final static Integer PARTNER_INTO= 1;
    //销售对象
    public final static Integer PARTNER_OUTTO = 2;



    /**
     * 操作日志模块操作类型信息
     * 0表示查询
     * 1表示添加
     * 2表示更新
     * 3表示删除
     * 4表示登录
     * 5表示统计
     * 6表示导出
     */
    public final static Integer FIND = 0;
    public final static Integer SAVE = 1;
    public final static Integer UPDATE = 2;
    public final static Integer DELETE = 3;
    public final static Integer LOGIN = 4;
    public final static Integer COUNT = 5;
    public final static Integer EXPORT = 6;
    public final static Integer UPDATE_PASSWORD = 7;


    public final static long ACTION_SUCCESS = 0;            //操作成功
    public final static long LOGIN_ERROR = 101;             //登录失败
    public final static long LOGIN_USERNAME_NULL = 102;     //登录名为空
    public final static long LOGIN_PASSWORD_NULL = 103;     //登录密码为空
    public final static long LOGOUT_ERROR = 104;            //退出失败
    public final static long PARAMS_ERROR = 105;            //参数错误
    public final static long NO_LOGIN = 106;                //未登录,请登录
    public final static long NO_REALM = 107;                //无权限
    public final static long NO_DATA = 108;                 //无数据
    public final static long LOGIN_PASSWORD_ERROR = 109;    //登录密码错误
    public final static long REQUEST_METHODS_ERROR = 110;    //请求方式错误


    //用户模块提示信息
    public final static long USER_NAME_NULL = 10001;        //用户名为空
    public final static long USER_NAME_REPEAT = 10002;        //用户名重复
    public final static long USER_ADDRESS_NULL = 10003;        //地址为空
    public final static long USER_MOBILE_NULL = 10004;        //联系方式为空
    public final static long USER_EXIST = 10005;        //用户已存在
    public final static long USER_NOT_EXIST = 10006;        //用户不存在
    public final static long USER_PASSWORD_NULL = 10007;        //用户密码为空
    public final static long USER_REALM_NULL = 10008;        //用户权限为空
    public final static long USER_UPDATE_ERROR = 10009;        //修改用户失败


    //厂商模块提示信息
    public final static long PARTNER_PNAME_NULL = 20001;
    public final static long PARTNER_USERNAME_NULL = 20002;
    public final static long PARTNER_MOBILE_NULL = 20003;
    public final static long PARTNER_ADDRESS_NULL = 20004;
    public final static long PARTNER_EXIST = 20005;
    public final static long PARTNER_MARK_NULL = 20006;
    public final static long PARTNER_NOT_EXIST = 20007;
    public final static long PARTNER_UPDATE_ERROR = 20008;
    public final static long PARTNER_NOT_OUTTO = 20009;

    public final static long MATERIAL_PARTNERID_NULL = 30001;
    public final static long MATERIAL_NAME_NULL = 30002;
    public final static long MATERIAL_TOTALMETER_NULL = 30003;
    public final static long MATERIAL_MONEY_NULL = 30004;
    public final static long MATERIAL_NOTEXIST = 30005;
    public final static long MATERIAL_UPDATE_ERROR = 30006;

    public final static long TYPE_NAME_NULL = 40001;
    public final static long TYPE_NAME_EXIST = 40002;
    public final static long TYPE_NOTEXIST = 40003;
    public final static long TYPE_UPDATE_ERROR = 40004;

    public final static long PRODUCTS_PRODUCTSNO_NULL = 50001;
    public final static long PRODUCTS_TYPEID_NULL = 50002;
    public final static long PRODUCTS_QUANTITY_NULL = 50003;
    public final static long PRODUCTS_NOT_EXIST = 50004;
    public final static long PRODUCTS_UPDATE_ERROR = 50005;
    public final static long PRODUCTS_UPDATEREASON_NULL = 50006;


    public final static long SALE_TYPEID_NULL = 60001;
    public final static long SALE_NUM_NULL = 60002;
    public final static long SALE_PARTNER_NULL = 60003;
    public final static long SALE_REMARK_NULL = 60004;
    public final static long SALE_NOT_EXIST = 60005;





    public static HashMap<Long, String> getTips() {
        HashMap<Long, String> map = new HashMap<>();

        map.put(ACTION_SUCCESS, "操作成功");
        map.put(LOGIN_ERROR, "登录失败");
        map.put(LOGIN_USERNAME_NULL, "登录名为空");
        map.put(LOGIN_PASSWORD_NULL, "登录密码为空");
        map.put(LOGOUT_ERROR, "退出失败");
        map.put(PARAMS_ERROR, "参数错误");
        map.put(NO_LOGIN, "未登录，请登录");
        map.put(NO_REALM, "无权限");
        map.put(NO_DATA, "无数据");
        map.put(LOGIN_PASSWORD_ERROR, "密码错误");

        map.put(USER_NAME_NULL, "用户名为空");
        map.put(USER_NAME_REPEAT, "用户名重复");
        map.put(USER_ADDRESS_NULL, "地址为空");
        map.put(USER_MOBILE_NULL, "联系方式为空");
        map.put(USER_EXIST, "用户已存在");
        map.put(USER_NOT_EXIST, "用户不存在");
        map.put(USER_PASSWORD_NULL, "用户密码为空");
        map.put(USER_REALM_NULL, "用户权限为空");
        map.put(USER_UPDATE_ERROR, "用户修改失败");

        map.put(PARTNER_PNAME_NULL, "商户名称为空");
        map.put(PARTNER_USERNAME_NULL, "联系人名称为空");
        map.put(PARTNER_MOBILE_NULL, "联系人电话为空");
        map.put(PARTNER_ADDRESS_NULL, "地址为空");
        map.put(PARTNER_EXIST, "厂商已存在");
        map.put(PARTNER_MARK_NULL, "厂商标识为空");
        map.put(PARTNER_NOT_EXIST, "厂商不存在");
        map.put(PARTNER_UPDATE_ERROR, "厂商修改失败");
        map.put(PARTNER_NOT_OUTTO, "非销售对象厂商");

        map.put(MATERIAL_PARTNERID_NULL, "厂商为空");
        map.put(MATERIAL_NAME_NULL, "原料名称为空");
        map.put(MATERIAL_TOTALMETER_NULL, "总数为空");
        map.put(MATERIAL_MONEY_NULL, "原料金额为空");
        map.put(MATERIAL_NOTEXIST, "原料信息不存在");
        map.put(MATERIAL_UPDATE_ERROR, "原材料数据更新失败");

        map.put(TYPE_NAME_NULL, "类型名称为空");
        map.put(TYPE_NAME_EXIST, "类型名称已存在");
        map.put(TYPE_NOTEXIST, "类型不存在");
        map.put(TYPE_UPDATE_ERROR, "类型修改失败");

        map.put(PRODUCTS_PRODUCTSNO_NULL, "产品标号为空");
        map.put(PRODUCTS_TYPEID_NULL, "产品类型为空");
        map.put(PRODUCTS_QUANTITY_NULL, "产品数量为空");
        map.put(PRODUCTS_NOT_EXIST, "产品不存在");
        map.put(PRODUCTS_UPDATE_ERROR, "产品更新失败");
        map.put(PRODUCTS_UPDATEREASON_NULL, "产品修改原因为空");


        map.put(SALE_TYPEID_NULL, "出货类型为空");
        map.put(SALE_NUM_NULL, "出货数量为空");
        map.put(SALE_PARTNER_NULL, "销售商户为空");
        map.put(SALE_REMARK_NULL, "修改备注为空");
        map.put(SALE_NOT_EXIST, "销售信息不存在");



        return map;
    }

    public static String getTip(Long key) {
        Map<Long, String> map = getTips();
        return map.get(key);
    }


}
