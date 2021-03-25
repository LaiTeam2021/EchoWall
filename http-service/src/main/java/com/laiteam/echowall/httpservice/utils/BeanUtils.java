package com.laiteam.echowall.httpservice.utils;



public class BeanUtils {
    private BeanUtils(){};

    public static<T, F> T copyProperties(F from, Class<T> tClass) {
        T newInstance = null;
        try {
            newInstance = tClass.newInstance();
            org.springframework.beans.BeanUtils.copyProperties(from, newInstance);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return newInstance;
    }
}
