package com.qiang.blog.entity;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author qiangqiang.dong
 * 
 */
public class BlogData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id ;
	
	@JSONField(name = "id")
	public String getId() {
		return id;
	}

	@JSONField(name = "id")
	public void setId(String id) {
		this.id = id;
	}

	private Guid guid;

	private String modified;

	private String modified_gmt;

	private String slug;

	private String type;

	private String link;

	private Title title;

	private Content content;

	private Excerpt excerpt;

	private int author;

	private int featured_media;

	private String comment_status;

	private String ping_status;

	private boolean sticky;

	private String format;

	private List<Categories> categories;

	private List<Tags> tags;

	private Links _links;

//	@JSONField(name = "guid")
//	public Guid getGuid() {
//		return guid;
//	}
//
//	@JSONField(name = "guid")
//	public void setGuid(Guid guid) {
//		this.guid = guid;
//	}

	// @JSONField(name="modified")
	// public String getModified() {
	// return modified;
	// }
	// @JSONField(name="modified")
	// public void setModified(String modified) {
	// this.modified = modified;
	// }
	//
	// @JSONField(name="modified_gmt")
	// public String getModified_gmt() {
	// return modified_gmt;
	// }
	//
	// @JSONField(name="modified_gmt")
	// public void setModified_gmt(String modified_gmt) {
	// this.modified_gmt = modified_gmt;
	// }
	//
	// @JSONField(name="slug")
	// public String getSlug() {
	// return slug;
	// }
	//
	// @JSONField(name="slug")
	// public void setSlug(String slug) {
	// this.slug = slug;
	// }
	//
	// @JSONField(name="type")
	// public String getType() {
	// return type;
	// }
	//
	// @JSONField(name="type")
	// public void setType(String type) {
	// this.type = type;
	// }
	//
	// @JSONField(name="link")
	// public String getLink() {
	// return link;
	// }
	//
	// @JSONField(name="link")
	// public void setLink(String link) {
	// this.link = link;
	// }
	//
	// @JSONField(name="title")
	// public Title getTitle() {
	// return title;
	// }
	//
	// @JSONField(name="title")
	// public void setTitle(Title title) {
	// this.title = title;
	// }
	//
	// @JSONField(name="content")
	// public Content getContent() {
	// return content;
	// }
	//
	// @JSONField(name="content")
	// public void setContent(Content content) {
	// this.content = content;
	// }
	//
	// @JSONField(name="excerpt")
	// public Excerpt getExcerpt() {
	// return excerpt;
	// }
	//
	// @JSONField(name="excerpt")
	// public void setExcerpt(Excerpt excerpt) {
	// this.excerpt = excerpt;
	// }
	//
	// @JSONField(name="author")
	// public int getAuthor() {
	// return author;
	// }
	//
	// @JSONField(name="author")
	// public void setAuthor(int author) {
	// this.author = author;
	// }
	//
	// @JSONField(name="featured_media")
	// public int getFeatured_media() {
	// return featured_media;
	// }
	//
	// @JSONField(name="featured_media")
	// public void setFeatured_media(int featured_media) {
	// this.featured_media = featured_media;
	// }
	//
	// @JSONField(name="comment_status")
	// public String getComment_status() {
	// return comment_status;
	// }
	//
	// @JSONField(name="comment_status")
	// public void setComment_status(String comment_status) {
	// this.comment_status = comment_status;
	// }
	//
	// @JSONField(name="ping_status")
	// public String getPing_status() {
	// return ping_status;
	// }
	//
	// @JSONField(name="ping_status")
	// public void setPing_status(String ping_status) {
	// this.ping_status = ping_status;
	// }
	//
	// @JSONField(name="sticky")
	// public boolean isSticky() {
	// return sticky;
	// }
	//
	// @JSONField(name="sticky")
	// public void setSticky(boolean sticky) {
	// this.sticky = sticky;
	// }
	//
	// @JSONField(name="format")
	// public String getFormat() {
	// return format;
	// }
	//
	// @JSONField(name="format")
	// public void setFormat(String format) {
	// this.format = format;
	// }
	//
	// @JSONField(name="categories")
	// public List<Categories> getCategories() {
	// return categories;
	// }
	//
	// @JSONField(name="categories")
	// public void setCategories(List<Categories> categories) {
	// this.categories = categories;
	// }
	//
	// @JSONField(name="tags")
	// public List<Tags> getTags() {
	// return tags;
	// }
	//
	// @JSONField(name="tags")
	// public void setTags(List<Tags> tags) {
	// this.tags = tags;
	// }
	//
	// @JSONField(name="_links")
	// public Links get_links() {
	// return _links;
	// }
	//
	// @JSONField(name="_links")
	// public void set_links(Links _links) {
	// this._links = _links;
	// }

}
