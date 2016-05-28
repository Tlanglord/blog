package com.qiang.blog.discovery.entity;

public class DiscoveryArticle {
    private String title;

    private String url;

    private String img;

    private String author;

    private int time;

    public void setTitle(String title) {
	this.title = title;
    }

    public String getTitle() {
	return this.title;
    }

    public void setUrl(String url) {
	this.url = url;
    }

    public String getUrl() {
	return this.url;
    }

    public void setImg(String img) {
	this.img = img;
    }

    public String getImg() {
	return this.img;
    }

    public void setAuthor(String author) {
	this.author = author;
    }

    public String getAuthor() {
	return this.author;
    }

    public void setTime(int time) {
	this.time = time;
    }

    public int getTime() {
	return this.time;
    }

}