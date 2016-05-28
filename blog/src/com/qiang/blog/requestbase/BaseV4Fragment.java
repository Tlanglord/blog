package com.qiang.blog.requestbase;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Window;

import com.qiang.blog.R;
import com.qiang.blog.activity.TabMainActivity;

public class BaseV4Fragment extends Fragment implements IMidResponse {

    private TabMainActivity mContext;
    private Dialog mDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	mContext = (TabMainActivity) getActivity();
	initProgress();
    }

    private void initProgress() {
	mDialog = new Dialog(mContext);
	mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	mDialog.getWindow().setDimAmount(0f);
	mDialog.getWindow().setBackgroundDrawable(
		new ColorDrawable(android.R.color.transparent));
	mDialog.setContentView(R.layout.loading);
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

    private void showProgress() {
	mDialog.show();
    }

    private void hideProgress() {
	mDialog.dismiss();
    }
}
