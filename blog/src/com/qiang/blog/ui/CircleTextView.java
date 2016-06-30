package com.qiang.blog.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class CircleTextView extends TextView {

    public CircleTextView(Context context, AttributeSet attrs,
	    int defStyleAttr, int defStyleRes) {
	super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CircleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
	super(context, attrs, defStyleAttr);
    }

    public CircleTextView(Context context, AttributeSet attrs) {
	super(context, attrs);
    }

    public CircleTextView(Context context) {
	super(context);
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
	canvas.drawColor(Color.WHITE);

	Paint paint = new Paint();

	//paint.setAlpha(a)
	paint.setAntiAlias(true);
	paint.setColor(Color.BLUE);
	paint.setStyle(Paint.Style.STROKE);
	paint.setStrokeWidth(3);

	// 绘制圆形
	canvas.drawCircle(40, 40, 30, paint);

        //super.onDraw(canvas);
    }
    
    

}
