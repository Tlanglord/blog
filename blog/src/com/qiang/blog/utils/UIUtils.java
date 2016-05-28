package com.qiang.blog.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
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
}
