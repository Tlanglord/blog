

package com.qiang.blog.movie.entity;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class Subjects implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Rating rating;

	private List<String> genres;

	private String title;

	private List<Casts> casts;

	private int collect_count;

	private String original_title;

	private String subtype;

	private List<Directors> directors;

	private String year;

	private Images images;

	private String alt;

	private String id;

	@JSONField(name="rating")
	public void setRating(Rating rating) {
		this.rating = rating;
	}
	
	@JSONField(name="rating")
	public Rating getRating() {
		return this.rating;
	}

	@JSONField(name="genres")
	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	@JSONField(name="genres")
	public List<String> getGenres() {
		return this.genres;
	}

	@JSONField(name="title")
	public void setTitle(String title) {
		this.title = title;
	}

	@JSONField(name="title")
	public String getTitle() {
		return this.title;
	}

	@JSONField(name="casts")
	public void setCasts(List<Casts> casts) {
		this.casts = casts;
	}

	@JSONField(name="casts")
	public List<Casts> getCasts() {
		return this.casts;
	}

	@JSONField(name="collect_count")
	public void setCollect_count(int collect_count) {
		this.collect_count = collect_count;
	}

	@JSONField(name="collect_count")
	public int getCollect_count() {
		return this.collect_count;
	}

	@JSONField(name="original_title")
	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}

	@JSONField(name="subtype")
	public String getOriginal_title() {
		return this.original_title;
	}

	@JSONField(name="subtype")
	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	@JSONField(name="subtype")
	public String getSubtype() {
		return this.subtype;
	}

	@JSONField(name="directors")
	public void setDirectors(List<Directors> directors) {
		this.directors = directors;
	}

	@JSONField(name="directors")
	public List<Directors> getDirectors() {
		return this.directors;
	}

	@JSONField(name="year")
	public void setYear(String year) {
		this.year = year;
	}

	@JSONField(name="year")
	public String getYear() {
		return this.year;
	}

	@JSONField(name="images")
	public void setImages(Images images) {
		this.images = images;
	}

	@JSONField(name="images")
	public Images getImages() {
		return this.images;
	}

	@JSONField(name="alt")
	public void setAlt(String alt) {
		this.alt = alt;
	}

	@JSONField(name="alt")
	public String getAlt() {
		return this.alt;
	}

	@JSONField(name="id")
	public void setId(String id) {
		this.id = id;
	}

	@JSONField(name="id")
	public String getId() {
		return this.id;
	}

}