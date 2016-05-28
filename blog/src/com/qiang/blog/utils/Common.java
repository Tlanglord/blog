package com.qiang.blog.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Common {
	
	public static SharedPreferences getSharedPreferences(Context context, String name, int mode ){
		return context.getSharedPreferences("app_guide_count",Context.MODE_PRIVATE);
	}
	
	public static SharedPreferences.Editor getSharedPreferencesEdit(Context context, String name, int mode){
		SharedPreferences sharedPreferences = getSharedPreferences(context, name, mode);
		return sharedPreferences == null ? null : sharedPreferences.edit();
	}

}
