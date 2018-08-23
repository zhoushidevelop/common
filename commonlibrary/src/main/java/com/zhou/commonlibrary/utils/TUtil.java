package com.zhou.commonlibrary.utils;

import java.lang.reflect.ParameterizedType;

/**
 * @author Administrator
 * @date 2018/7/13 0013
 * @des
 */
public class TUtil {
    public static <T> T getT(Object o, int i) {
        try {
            ParameterizedType types = (ParameterizedType) o.getClass().getGenericSuperclass();
            return ((Class<T>) types.getActualTypeArguments()[i]).newInstance();
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        } catch (ClassCastException e) {
        }
        return null;
    }

    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
