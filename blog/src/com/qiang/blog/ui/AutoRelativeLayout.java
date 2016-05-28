package com.qiang.blog.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class AutoRelativeLayout extends RelativeLayout {

	@SuppressLint("NewApi")
	public AutoRelativeLayout(Context context, AttributeSet attrs,
			int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	public AutoRelativeLayout(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public AutoRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AutoRelativeLayout(Context context) {
		super(context);
	}
}
