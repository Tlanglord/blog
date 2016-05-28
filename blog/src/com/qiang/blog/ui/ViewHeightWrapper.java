package com.qiang.blog.ui;

import android.view.View;

public class ViewHeightWrapper {
    private View view;

    public ViewHeightWrapper(View view) {
	super();
	this.view = view;
    }

    public void setHeight(int height) {
	view.getLayoutParams().height = height;
	view.requestLayout();
    }

    public int getHeight() {
	return view.getLayoutParams().height;
    }
}