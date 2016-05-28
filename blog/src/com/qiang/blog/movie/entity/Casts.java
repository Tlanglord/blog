package com.qiang.blog.movie.entity;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class Casts implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String alt;

	private Avatars avatars;

	private String name;

	private String id;

	@JSONField(name = "alt")
	public void setAlt(String alt) {
		this.alt = alt;
	}

	@JSONField(name = "alt")
	public String getAlt() {
		return this.alt;
	}

	@JSONField(name = "avatars")
	public void setAvatars(Avatars avatars) {
		this.avatars = avatars;
	}

	@JSONField(name = "avatars")
	public Avatars getAvatars() {
		return this.avatars;
	}

	@JSONField(name = "name")
	public void setName(String name) {
		this.name = name;
	}

	@JSONField(name = "name")
	public String getName() {
		return this.name;
	}

	@JSONField(name = "id")
	public void setId(String id) {
		this.id = id;
	}

	@JSONField(name = "id")
	public String getId() {
		return this.id;
	}

}
