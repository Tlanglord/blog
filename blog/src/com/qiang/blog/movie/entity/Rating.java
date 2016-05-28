package com.qiang.blog.movie.entity;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class Rating implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int max;

	private int average;

	private String stars;

	private int min;
  
    @JSONField(name="max")  
	public void setMax(int max) {
		this.max = max;
	}

    @JSONField(name="max")  
	public int getMax() {
		return this.max;
	}

    @JSONField(name="average")  
	public void setAverage(int average) {
		this.average = average;
	}

    @JSONField(name="average")  
	public int getAverage() {
		return this.average;
	}

    @JSONField(name="stars")  
	public void setStars(String stars) {
		this.stars = stars;
	}

    @JSONField(name="stars")  
	public String getStars() {
		return this.stars;
	}

    @JSONField(name="min")  
	public void setMin(int min) {
		this.min = min;
	}

    @JSONField(name="min")  
	public int getMin() {
		return this.min;
	}

}
