package com.qiang.blog.ui;

import com.umeng.analytics.c;

import android.R.integer;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.StackView;

public class MyStackView extends StackView {

	@SuppressLint("NewApi")
	public MyStackView(Context context, AttributeSet attrs, int defStyleAttr,
			int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
	}

	public MyStackView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public MyStackView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyStackView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	
	/* (non-Javadoc)
	 * @see android.widget.StackView#onLayout(boolean, int, int, int, int)
	 */
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		//checkForAndHandleDataChanged();
		//super.onLayout(changed, left, top, right, bottom);
		
		final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);

            int childRight = child.getMeasuredWidth();
            int childBottom =  child.getMeasuredHeight();
            LayoutParams lp = (LayoutParams) child.getLayoutParams();

            child.layout(0,0,
                    childRight, childBottom);
            
        }
        
        for(int j = 0; j < getChildCount(); j++){
        	View child = getChildAt(j);
        	//child.setTranslationX(20*j);
        	child.setTranslationY(300*j);
        	
        }
        //onLayout();
	}
	
}
