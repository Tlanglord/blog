package com.qiang.blog.movie.entity;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class Avatars implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String small;

	private String large;

	private String medium;

	
	@JSONField(name="small")
	public void setSmall(String small) {
		this.small = small;
	}

	@JSONField(name="small")
	public String getSmall() {
		return this.small;
	}

	@JSONField(name="large")
	public void setLarge(String large) {
		this.large = large;
	}

	@JSONField(name="large")
	public String getLarge() {
		return this.large;
	}

	@JSONField(name="medium")
	public void setMedium(String medium) {
		this.medium = medium;
	}

	@JSONField(name="medium")
	public String getMedium() {
		return this.medium;
	}

}
