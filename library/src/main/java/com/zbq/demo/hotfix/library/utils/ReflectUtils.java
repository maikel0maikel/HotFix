package com.zbq.demo.hotfix.library.utils;

import java.lang.reflect.Field;


public class ReflectUtils {


    public static final Object getDexElements(Object dexPathList) throws NoSuchFieldException, IllegalAccessException {
       return getField(dexPathList,dexPathList.getClass(),"dexElements");
    }

    public static final Object getPathList(Object baseDexClassLoader) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        return getField(baseDexClassLoader,Class.forName("dalvik.system.BaseDexClassLoader"),"pathList");
    }

    private static final Object getField(Object object,Class<?> cls,String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = cls.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }

    public static final void setFileValue(Object object,Class<?> cls,Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = cls.getDeclaredField("dexElements");
        field.setAccessible(true);
        field.set(object,value);
    }
}
