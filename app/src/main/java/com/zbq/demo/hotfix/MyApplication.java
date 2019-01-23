package com.zbq.demo.hotfix;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.zbq.demo.hotfix.library.utils.HotFixUtils;

public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(context);
        HotFixUtils.loadDex(context);
    }
}
