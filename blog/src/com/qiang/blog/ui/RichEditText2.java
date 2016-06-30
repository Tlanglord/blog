package com.qiang.blog.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.View.OnLongClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.qiang.blog.R;

public class RichEditText2 extends EditText implements OnLongClickListener,TextWatcher,
	 OnFocusChangeListener, OnEditorActionListener,
	OnKeyListener {

    private Context mContext;
    private String imgUrl = "";
    private Editable mEditable;

    public void notifyDataSetChanged() {
	addImage(BitmapFactory.decodeFile(imgUrl), imgUrl);
    }

    public void addDefaultImage(String filePath, int start, int end) {
	Log.i("imgpath", filePath);
	String pathTag = "<img src=\"" + filePath + "\"/>";
	SpannableString spanString = new SpannableString(pathTag);
	Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
		R.drawable.ic_launcher);
	int paddingLeft = getPaddingLeft();
	int paddingRight = getPaddingRight();
	int bmWidth = bitmap.getWidth();//
	int bmHeight = bitmap.getHeight();//
	int zoomWidth = getWidth() - (paddingLeft + paddingRight);
	int zoomHeight = (int) (((float) zoomWidth / (float) bmWidth) * bmHeight);
	Bitmap newBitmap = zoomImage(bitmap, zoomWidth, zoomHeight);
	ImageSpan imgSpan = new ImageSpan(mContext, newBitmap);
	spanString.setSpan(imgSpan, 0, pathTag.length(),
		Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	if (mEditable == null) {
	    mEditable = this.getText();
	}
	mEditable.delete(start, end);//
	mEditable.insert(start, spanString);
    }

    public void addImage(Bitmap bitmap, String filePath) {
	Log.i("imgpath", filePath);
	String pathTag = "<img src=\"" + filePath + "\"/>";
	SpannableString spanString = new SpannableString(pathTag);
	int paddingLeft = getPaddingLeft();
	int paddingRight = getPaddingRight();
	int bmWidth = bitmap.getWidth();//
	int bmHeight = bitmap.getHeight();//
	int zoomWidth = (getWidth() - (paddingLeft + paddingRight)) * 9 / 14;

	if (bmHeight > zoomWidth) {
	    bmHeight = bmHeight * 9 / 20;
	}
	// int zoomHeight = (int) (((float) zoomWidth / (float) bmWidth) *
	// bmHeight);
	int zoomHeight = bmHeight;
	Bitmap newBitmap = zoomImage(bitmap, zoomWidth, zoomHeight);
	ImageSpan imgSpan = new ImageSpan(mContext, newBitmap);
	spanString.setSpan(imgSpan, 0, pathTag.length(),
		Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	if (mEditable == null) {
	    mEditable = this.getText(); //
	}
	int start = this.getSelectionStart(); //

	if (spanString.length() < start) {
	    start = spanString.length();
	}
	mEditable.insert(start, spanString); //
	this.setText(mEditable);
	this.setSelection(start, spanString.length());
    }

    private Bitmap zoomImage(Bitmap bgimage, double zoomW, double zoomH) {
	float w = bgimage.getWidth();
	float h = bgimage.getHeight();
	if (zoomW == 0) {
	    zoomW = w;
	    zoomH = h;
	}
	Matrix matrix = new Matrix();
	float scaleW = ((float) zoomW) / w;
	float scaleH = ((float) zoomH) / h;
	matrix.postScale(scaleW, scaleH);
	// Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) w,
	// (int) h, matrix, true);
	Bitmap bitmap = Bitmap.createScaledBitmap(bgimage, (int) zoomW,
		(int) zoomH, true);
	return bitmap;
    }

    public void setImgUrl(String imgUrl) {
	this.imgUrl = imgUrl;
    }

    public RichEditText2(Context context, AttributeSet attrs, int defStyleAttr,
	    int defStyleRes) {
	super(context, attrs, defStyleAttr, defStyleRes);
	mContext = context;
	initEvent();
    }

    public RichEditText2(Context context, AttributeSet attrs, int defStyleAttr) {
	super(context, attrs, defStyleAttr);
	mContext = context;
	initEvent();
    }

    public RichEditText2(Context context, AttributeSet attrs) {
	super(context, attrs);
	mContext = context;
	initEvent();
    }

    private void initEvent() {
	setOnLongClickListener(this);
	setOnEditorActionListener(this);
	setOnFocusChangeListener(this);
	setOnKeyListener(this);
	addTextChangedListener(this);
	setSelection(0);
	requestFocus();
	setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    public RichEditText2(Context context) {
	super(context);
	mContext = context;
	setOnLongClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
	// int width = getMeasuredWidth();
	// int height = getMeasuredHeight();
	// FontMetrics fontMetrics = getPaint().getFontMetrics();
	//
	// int textH = (int) (fontMetrics.bottom - fontMetrics.top +
	// fontMetrics.leading);
	//
	// Paint paint = new Paint();
	// paint.setColor(Color.parseColor("#4499ff"));
	// paint.setStrokeWidth(1f);
	// paint.setStyle(Paint.Style.STROKE);
	// paint.setAntiAlias(true);
	// int text2H = (int) Math.ceil(fontMetrics.descent -
	// fontMetrics.ascent);
	// int y =(int)(+getTextSize());
	// int line1H = (int) (fontMetrics.bottom - fontMetrics.top +
	// fontMetrics.leading + 2);
	// // inttextHeight = (int) (Math.ceil(fm.descent - fm.top) + 2);
	// // + textH + getLineSpacingExtra()
	//
	// int text3H = (int) (textH + getLineSpacingExtra() * 1.1);
	// int lineH = (int) (getLineHeight()+getTextSize());
	// for (int i = 0; i < height / lineH; i++) {
	// //canvas.drawLine(0, y+2, width, y+2, paint);
	// // y+=getLineHeight();
	// }

	Editable editable = getText();
	String string = editable.toString();
	super.onDraw(canvas);
    }

    @Override
    public boolean onLongClick(View v) {
	return false;
    }

    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore,
	    int lengthAfter) {
	super.onTextChanged(text, start, lengthBefore, lengthAfter);
	String keyWord = text.toString();
	
    }
    

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
	switch (event.getAction()) {
	case KeyEvent.KEYCODE_ENTER:
	    break;
	}

	return false;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
	return false;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
	super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
	    int after) {
	
    }

    @Override
    public void afterTextChanged(Editable s) {
	
    }

}
