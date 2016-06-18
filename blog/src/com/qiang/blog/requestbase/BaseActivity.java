package com.qiang.blog.requestbase;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

import com.qiang.blog.R;

public abstract class BaseActivity extends Activity implements IMidResponse, OnGestureListener {

    private Dialog mDialog;
    private GestureDetector mGestureDetector; 
    
    
    
    private void initGesture(){
	mGestureDetector = new GestureDetector(this);   
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	if (onContentView() != 0) {
	    setContentView(onContentView());
	}
	onInitView();
	onInitEvent();
	setBack(R.id.header_back);
	initProgress();
    }

    private void initProgress() {
	mDialog = new Dialog(this);
	mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	mDialog.getWindow().setDimAmount(0f);
	mDialog.getWindow().setBackgroundDrawable(
		new ColorDrawable(android.R.color.transparent));
	mDialog.setContentView(R.layout.loading);
    }

    private void showProgress() {
	mDialog.show();
    }

    private void hideProgress() {
	mDialog.dismiss();
    }

    public void requestConnection(RequestWrapper reqWrp) {
	requestConnection(reqWrp, this);
    }

    public void requestConnection(RequestWrapper reqWrp, IMidResponse iResponse) {
	reqWrp.setResponse(iResponse);
	showProgress();
	VolleyRequestManager.executeRequest(reqWrp.getRequest());
    }

    @Override
    public void onResponse(Object tag, String response) {
	hideProgress();
    }

    protected void back() {
	finish();
    }

    protected void setBack(int id) {
	if (findViewById(id) != null) {
	    findViewById(id).setOnClickListener(backListener);
	}
    }

    protected abstract int onContentView();

    protected abstract void onInitView();

    protected abstract void onInitEvent();

    private OnClickListener backListener = new OnClickListener() {

	@Override
	public void onClick(View v) {
	    back();
	}
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
	if (KeyEvent.KEYCODE_BACK == keyCode) {
	    back();
	}
	return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
	return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
	
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
	return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
	    float distanceY) {
	return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
	
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
	    float velocityY) {
	return false;
    }
    
    

}
