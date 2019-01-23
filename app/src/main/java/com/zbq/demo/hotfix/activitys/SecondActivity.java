package com.zbq.demo.hotfix.activitys;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;

import com.zbq.demo.hotfix.BaseActivity;
import com.zbq.demo.hotfix.R;
import com.zbq.demo.hotfix.library.constant.Constants;
import com.zbq.demo.hotfix.library.utils.FileUtils;
import com.zbq.demo.hotfix.library.utils.HotFixUtils;
import com.zbq.demo.hotfix.utils.CalUtils;

import java.io.File;
import java.io.IOException;


public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void cal(View view) {
        CalUtils.cal(this);
    }

    public void fix(View view) {
        fixBug();
    }

    /**
     * 修复
     */
    private void fixBug(){
        //修复的dex文件路径
        String fixDex = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator+Constants.HOT_FIX_DEX_NAME;
        File sourceFile = new File(fixDex);
        //要复制到的文件路径
        String targetFilePath = getDir(Constants.TMP_DIR,Context.MODE_PRIVATE).getAbsolutePath()+File.separator+Constants.HOT_FIX_DEX_NAME;
        File targetFile = new File(targetFilePath);
        //如果文件存在则删除
        if (targetFile.exists()){
            targetFile.delete();
        }
        try {
            //开始拷贝
            FileUtils.copy(sourceFile,targetFile);
            //开始修复
            HotFixUtils.loadDex(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
