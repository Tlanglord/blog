package com.qiang.blog.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppContext {

    private static AppContext mDemoContext;
    public Context mContext;
    private SharedPreferences mPreferences;

    public static AppContext getInstance() {

        if (mDemoContext == null) {
            mDemoContext = new AppContext();
        }
        return mDemoContext;
    }

    private AppContext() {
    }

    private AppContext(Context context) {
        mContext = context;
        mDemoContext = this;
        //http初始化 用于登录、注册使用
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);

    }


    public static void init(Context context) {
        mDemoContext = new AppContext(context);
    }

    public SharedPreferences getSharedPreferences() {
        return mPreferences;
    }

}
