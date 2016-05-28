package com.qiang.blog.movie.entity;

import java.io.Serializable;
import java.util.List;

public class MovieInTheater implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int count;

	private int start;

	private int total;

	private List<Subjects> subjects;

	private String title;

	//@JSONField(name="count")
	public void setCount(int count) {
		this.count = count;
	}

	//@JSONField(name="count")
	public int getCount() {
		return this.count;
	}

	
	//@JSONField(name="start")
	public void setStart(int start) {
		this.start = start;
	}

	//@JSONField(name="start")
	public int getStart() {
		return this.start;
	}

	//@JSONField(name="total")
	public void setTotal(int total) {
		this.total = total;
	}

	//@JSONField(name="total")
	public int getTotal() {
		return this.total;
	}

	//@JSONField(name="subjects")
	public void setSubjects(List<Subjects> subjects) {
		this.subjects = subjects;
	}

	//@JSONField(name="subjects")
	public List<Subjects> getSubjects() {
		return this.subjects;
	}
	
	//@JSONField(name="title")
	public void setTitle(String title) {
		this.title = title;
	}

	//@JSONField(name="title")
	public String getTitle() {
		return this.title;
	}
}
