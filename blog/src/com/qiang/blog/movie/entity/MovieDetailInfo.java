package com.qiang.blog.movie.entity;

import java.util.List;

public class MovieDetailInfo {
	private Rating rating;

	private int reviews_count;

	private int wish_count;

	private String douban_site;

	private String year;

	private Images images;

	private String alt;

	private String id;

	private String mobile_url;

	private String title;

	private String share_url;

	private String schedule_url;

	private List<String> countries;

	private List<String> genres;

	private int collect_count;

	private List<Casts> casts;

	private String original_title;

	private String summary;

	private String subtype;

	private List<Directors> directors;

	private int comments_count;

	private int ratings_count;

	private List<String> aka;

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public Rating getRating() {
		return this.rating;
	}

	public void setReviews_count(int reviews_count) {
		this.reviews_count = reviews_count;
	}

	public int getReviews_count() {
		return this.reviews_count;
	}

	public void setWish_count(int wish_count) {
		this.wish_count = wish_count;
	}

	public int getWish_count() {
		return this.wish_count;
	}

	public void setDouban_site(String douban_site) {
		this.douban_site = douban_site;
	}

	public String getDouban_site() {
		return this.douban_site;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getYear() {
		return this.year;
	}

	public void setImages(Images images) {
		this.images = images;
	}

	public Images getImages() {
		return this.images;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getAlt() {
		return this.alt;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setMobile_url(String mobile_url) {
		this.mobile_url = mobile_url;
	}

	public String getMobile_url() {
		return this.mobile_url;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setShare_url(String share_url) {
		this.share_url = share_url;
	}

	public String getShare_url() {
		return this.share_url;
	}

	public void setSchedule_url(String schedule_url) {
		this.schedule_url = schedule_url;
	}

	public String getSchedule_url() {
		return this.schedule_url;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	public List<String> getCountries() {
		return this.countries;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public List<String> getGenres() {
		return this.genres;
	}

	public void setCollect_count(int collect_count) {
		this.collect_count = collect_count;
	}

	public int getCollect_count() {
		return this.collect_count;
	}

	public void setCasts(List<Casts> casts) {
		this.casts = casts;
	}

	public List<Casts> getCasts() {
		return this.casts;
	}

	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}

	public String getOriginal_title() {
		return this.original_title;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public String getSubtype() {
		return this.subtype;
	}

	public void setDirectors(List<Directors> directors) {
		this.directors = directors;
	}

	public List<Directors> getDirectors() {
		return this.directors;
	}

	public void setComments_count(int comments_count) {
		this.comments_count = comments_count;
	}

	public int getComments_count() {
		return this.comments_count;
	}

	public void setRatings_count(int ratings_count) {
		this.ratings_count = ratings_count;
	}

	public int getRatings_count() {
		return this.ratings_count;
	}

	public void setAka(List<String> aka) {
		this.aka = aka;
	}

	public List<String> getAka() {
		return this.aka;
	}

}