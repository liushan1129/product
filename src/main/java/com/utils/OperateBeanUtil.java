package com.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class OperateBeanUtil {

    public static  <T> T setPackBean(T t, T oldT) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Class clazz = oldT.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String key = field.getName();
            PropertyDescriptor descriptor = new PropertyDescriptor(key, clazz);
            Method method = descriptor.getReadMethod();
            Object value = method.invoke(t);
            // 获取set方法，将获取的新值value放到oldUser中
            if(value != null){
                Method set = descriptor.getWriteMethod();
                set.invoke(oldT, value);
            }
        }
        return (T) oldT;
    }

}
