package com.utils;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public abstract class CommonUtil {
    //格式化时间
    public static Timestamp formatDate(Timestamp date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return Timestamp.valueOf(sdf.format(date));
    }
}
