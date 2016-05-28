package com.qiang.blog.ui;

import android.view.View;

public class ViewWidthWrapper {
    private View view;

    public ViewWidthWrapper(View view) {
	super();
	this.view = view;
    }

    public void setWidth(int width) {
	view.getLayoutParams().width = width;
	view.requestLayout();
    }

    public int getWidth() {
	return view.getLayoutParams().width;
    }
}