package com.qiang.blog.utils;

import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class UIUtils {
    public static void setStatusColor(Activity activity, String color) {
	setStatusColor(activity, Color.parseColor(color));
    }

    public static void setStatusColor(Activity activity, int color) {
	Window window = activity.getWindow();
	if (VERSION.SDK_INT <= VERSION_CODES.KITKAT) {
	    window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
	    ViewGroup decorView = (ViewGroup) window.getDecorView();
	    View statusView = createStatusView(activity, color);
	    decorView.addView(statusView);
	} else {
	    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
	    window.getDecorView().setSystemUiVisibility(
		    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
			    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
	    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
	    window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
	    window.setStatusBarColor(color);

	}
    }

    private static View createStatusView(Activity activity, int color) {
	int resourceId = activity.getResources().getIdentifier(
		"status_bar_height", "dimen", "android");
	int statusBarHeight = activity.getResources().getDimensionPixelSize(
		resourceId);
	View statusView = new View(activity);
	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
		ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
	statusView.setLayoutParams(params);
	statusView.setBackgroundColor(color);
	return statusView;
    }

    public static int px2dp(Context context, float pxValue) {
	final float scale = context.getResources().getDisplayMetrics().density;
	return (int) (pxValue / scale + 0.5f);
    }

    public static int dp2px(Context context, float dpValue) {
	final float scale = context.getResources().getDisplayMetrics().density;
	return (int) (dpValue * scale + 0.5f);
    }

    public static int getScreenWidth(Context context) {
	DisplayMetrics dm = context.getResources().getDisplayMetrics();
	return dm.widthPixels;
    }

    public static int getScreenHeight(Context context) {
	DisplayMetrics dm = context.getResources().getDisplayMetrics();
	return dm.heightPixels;
    }

    public static String getAppVersionName(Context context) {
	try {
	    PackageManager mPackageManager = context.getPackageManager();
	    PackageInfo _info = mPackageManager.getPackageInfo(
		    context.getPackageName(), 0);
	    return _info.versionName;
	} catch (NameNotFoundException e) {
	    return null;
	}
    }

    public static int getAppVersionCode(Context context) {
	try {
	    PackageManager mPackageManager = context.getPackageManager();
	    PackageInfo _info = mPackageManager.getPackageInfo(
		    context.getPackageName(), 0);
	    return _info.versionCode;
	} catch (NameNotFoundException e) {
	    return 0;
	}
    }

    public static Bitmap BitmapZoom(Bitmap b, float x, float y) {
	int w = b.getWidth();
	int h = b.getHeight();
	float sx = x / w;
	float sy = y / h;
	Matrix matrix = new Matrix();
	matrix.postScale(sx, sy);
	Bitmap resizeBmp = Bitmap.createBitmap(b, 0, 0, w, h, matrix, true);
	return resizeBmp;
    }

    /**
     * 判断应用是否处于后台状态
     * 
     * @return
     */
    public static boolean isBackground(Context context) {
	ActivityManager am = (ActivityManager) context
		.getSystemService(Context.ACTIVITY_SERVICE);
	List<RunningTaskInfo> tasks = am.getRunningTasks(1);
	if (!tasks.isEmpty()) {
	    ComponentName topActivity = tasks.get(0).topActivity;
	    if (!topActivity.getPackageName().equals(context.getPackageName())) {
		return true;
	    }
	}
	return false;
    }
}
