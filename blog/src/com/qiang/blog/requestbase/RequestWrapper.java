package com.qiang.blog.requestbase;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class RequestWrapper {
    private StringRequest mRequest;
    private Object mTag;
    private String mUrl;
    private IMidResponse mResponse;
    private Map<String, String> mHeader = new HashMap<String, String>();
    private Map<String, String> mParams = new HashMap<String, String>();

    public Map<String, String> getParamerts() {
	return mParams;
    }

    public void setParamters(Map<String, String> mParams) {
	this.mParams = mParams;
    }

    public void setParamter(String key, String value) {
	if (mParams != null) {
	    mParams = new HashMap<String, String>();
	}
	mParams.put(key, value);
    }

    public Map<String, String> getHeader() {
	return mHeader;
    }

    public void setHeader(Map<String, String> mHeader) {
	this.mHeader = mHeader;
    }

    public void setHeader(String key, String value) {
	if (mHeader == null) {
	    mHeader = new HashMap<String, String>();
	}
	mHeader.put(key, value);
    }

    public String getUrl() {
	return mUrl;
    }

    public void setUrl(String url) {
	this.mUrl = url;
    }

    public Object getTag() {
	return mTag;
    }

    public void setTag(Object tag) {
	this.mTag = tag;
    }

    public IMidResponse getResponse() {
	return mResponse;
    }

    public void setResponse(IMidResponse response) {
	this.mResponse = response;
    }

    public StringRequest getRequest() {
	mRequest = new StringRequest(getUrl(), new Response.Listener<String>() {
	    @Override
	    public void onResponse(String response) {
		mResponse.onResponse(mTag, response);
	    }
	}, new Response.ErrorListener() {
	    @Override
	    public void onErrorResponse(VolleyError error) {
		mResponse.onResponse(mTag, null);
	    }
	}) {
	    @Override
	    public Map<String, String> getHeaders() throws AuthFailureError {
		return getHeader();
	    }

	    @Override
	    public byte[] getBody() throws AuthFailureError {
		return super.getBody();
	    }

	    @Override
	    protected Map<String, String> getParams() throws AuthFailureError {
		return getParamerts();
	    }

	    @Override
	    public int getMethod() {
		return super.getMethod();
	    }

	    @Override
	    protected String getParamsEncoding() {
		return super.getParamsEncoding();
	    }
	};
	mRequest.setTag(mTag);
	return mRequest;
    }
}
