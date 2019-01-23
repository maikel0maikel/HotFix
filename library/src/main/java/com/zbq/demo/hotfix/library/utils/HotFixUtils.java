package com.zbq.demo.hotfix.library.utils;

import android.content.Context;

import com.zbq.demo.hotfix.library.constant.Constants;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

public class HotFixUtils {
    //用来临时存放已修复的dex文件
    private static List<File> dexFileLists  = new ArrayList<>();

    /**
     * 加载已修复的dex文件
     * @param context 上下文
     */
    public static void loadDex(Context context){
        if (context == null)return;
        dexFileLists.clear();//修复之前清除一次
        File dexDirFile = context.getDir(Constants.TMP_DIR,Context.MODE_PRIVATE);
        File [] dexFiles = dexDirFile.listFiles();
        for (File file:dexFiles){
            //如果是.dex文件并且不是主dex则把dex加入到集合中
            if (file.getName().endsWith(Constants.DEX_SUFFIX)&&!"classes.dex".equals(file.getName())){
                dexFileLists.add(file);
            }
        }
        //创建dexClassLoader
        createBaseDexClassLoader(context, dexDirFile);

    }

    /**
     * 创建classLoader
     * @param context 上下文
     * @param dexDirFile 存放已修复的dex目录文件
     */
    private static void createBaseDexClassLoader(Context context, File dexDirFile) {
        //创建解压目录
        File unpackDexDir = new File(dexDirFile.getAbsolutePath()+File.separator+"opt_dex");
        if (!unpackDexDir.exists()){
            unpackDexDir.mkdirs();
        }
        //每遍历一个需要热修复的dex文件就需要插装一次
        for (File file:dexFileLists){
            //创建DexClassLoader
            DexClassLoader dexClassLoader = new DexClassLoader(file.getAbsolutePath(),
                    unpackDexDir.getAbsolutePath(),null,context.getClassLoader());
            //热修复
            hotFix(dexClassLoader,context);
        }

    }

    /**
     * 热修复
     * @param dexClassLoader 已修复的DexClassLoader
     * @param context 上下文
     */
    private static void hotFix(DexClassLoader dexClassLoader, Context context) {
        //获取系统PathClassLoader
        PathClassLoader pathClassLoader = (PathClassLoader) context.getClassLoader();
        try {
            //获取私有的pathList的dexElements
            Object myDexElements = ReflectUtils.getDexElements(ReflectUtils.getPathList(dexClassLoader));
            //获取系统的dexElements
            Object systemDexElements = ReflectUtils.getDexElements(ReflectUtils.getPathList(pathClassLoader));
            //把私有的dexElements与系统的dexElements合并成新的dexElements
            Object newDexElements = CombineUtils.combineArray(myDexElements,systemDexElements);
            //通过反射再次去获取系统的pathList对象
            Object systemPathList = ReflectUtils.getPathList(pathClassLoader);
            //重新赋值给系统pathClassLoader里的PathList属性值
            ReflectUtils.setFileValue(systemPathList,systemPathList.getClass(),newDexElements);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
