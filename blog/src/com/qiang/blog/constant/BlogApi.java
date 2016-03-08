package com.qiang.blog.constant;

public enum BlogApi {
	
	getBlogContent("posts");
	
	private String mAction;
	
	BlogApi(String actionName) {
		this.mAction = actionName;
	}
	
	public  String getAction(){
		return ApiHead.API_HEAD + mAction;
	}
	
	@Override
	public String toString() {
		return ApiHead.API_HEAD + mAction;
	}
	
}
