package com.zbq.demo.hotfix.library.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {

    public static void copy(File sourceFile,File targetFile) throws IOException {
        FileInputStream fis;
        FileOutputStream fos;

        fis = new FileInputStream(sourceFile);
        fos = new FileOutputStream(targetFile);
        byte[]buffer = new byte[1024];
        int len = -1;
        while ((len = fis.read(buffer))!= -1){
            fos.write(buffer,0,len);
            fos.flush();
        }
        fis.close();
        fos.close();
    }
}
