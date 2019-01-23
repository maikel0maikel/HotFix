package com.zbq.demo.hotfix.utils;

import android.content.Context;
import android.widget.Toast;

import com.zbq.demo.hotfix.R;


public class CalUtils {

    public static void cal(Context context){
        int x = 10;
        int y = 1;
        int result = x/y;
        Toast.makeText(context,String.format(context.getResources().getString(R.string.cal_result_text),result),Toast.LENGTH_SHORT).show();
    }
}
