package com.qiang.blog.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.EditText;

public class RichEditText1 extends EditText implements OnLongClickListener{

    public RichEditText1(Context context, AttributeSet attrs, int defStyleAttr,
	    int defStyleRes) {
	super(context, attrs, defStyleAttr, defStyleRes);
    }

    public RichEditText1(Context context, AttributeSet attrs, int defStyleAttr) {
	super(context, attrs, defStyleAttr);
    }

    public RichEditText1(Context context, AttributeSet attrs) {
	super(context, attrs);
    }

    public RichEditText1(Context context) {
	super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
	super.onDraw(canvas);
    }

    @Override
    public boolean onLongClick(View v) {
	return false;
    }
    
    
    

}
