package com.qiang.blog.entity;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author qiangqiang.dong
 * 
 */
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private String name;

	private String url;

	private String description;

	private String link;

	private String slug;

	@JSONField(name = "id")
	public void setId(int id) {
		this.id = id;
	}

	@JSONField(name = "id")
	public int getId() {
		return this.id;
	}

	// public void setName(String name) {
	// this.name = name;
	// }
	//
	// public String getName() {
	// return this.name;
	// }
	//
	// public void setUrl(String url) {
	// this.url = url;
	// }
	//
	// public String getUrl() {
	// return this.url;
	// }
	//
	// public void setDescription(String description) {
	// this.description = description;
	// }
	//
	// public String getDescription() {
	// return this.description;
	// }
	//
	// public void setLink(String link) {
	// this.link = link;
	// }
	//
	// public String getLink() {
	// return this.link;
	// }
	//
	// public void setSlug(String slug) {
	// this.slug = slug;
	// }
	//
	// public String getSlug() {
	// return this.slug;
	// }

}