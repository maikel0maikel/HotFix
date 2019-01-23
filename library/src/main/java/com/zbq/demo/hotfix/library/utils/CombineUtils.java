package com.zbq.demo.hotfix.library.utils;

import java.lang.reflect.Array;

public class CombineUtils {

    public static Object combineArray(Object array1, Object array2) {

        int arrayLen1 = Array.getLength(array1);

        int arrayLen2 = Array.getLength(array2);

        int newLen = arrayLen1 + arrayLen2;

        Object newArray = Array.newInstance(array1.getClass().getComponentType(), newLen);

        for (int i = 0; i < newLen; i++) {
            if (i < arrayLen1) {
                Array.set(newArray, i, Array.get(array1, i));
            } else {
                //插入到后面
                Array.set(newArray, i, Array.get(array2, i-arrayLen1));
            }
        }
        return newArray;
    }

}
