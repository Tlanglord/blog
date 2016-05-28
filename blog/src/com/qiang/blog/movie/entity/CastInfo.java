package com.qiang.blog.movie.entity;

import java.util.List;

public class CastInfo {
    private String mobile_url;

    private List<String> aka_en;

    private String name;

    private List<Works> works;

    private String gender;

    private Avatars avatars;

    private String id;

    private List<String> aka;

    private String name_en;

    private String born_place;

    private String alt;

    public void setMobile_url(String mobile_url) {
	this.mobile_url = mobile_url;
    }

    public String getMobile_url() {
	return this.mobile_url;
    }

    public void setAka_en(List<String> aka_en) {
	this.aka_en = aka_en;
    }

    public List<String> getAka_en() {
	return this.aka_en;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getName() {
	return this.name;
    }

    public void setWorks(List<Works> works) {
	this.works = works;
    }

    public List<Works> getWorks() {
	return this.works;
    }

    public void setGender(String gender) {
	this.gender = gender;
    }

    public String getGender() {
	return this.gender;
    }

    public void setAvatars(Avatars avatars) {
	this.avatars = avatars;
    }

    public Avatars getAvatars() {
	return this.avatars;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getId() {
	return this.id;
    }

    public void setAka(List<String> aka) {
	this.aka = aka;
    }

    public List<String> getAka() {
	return this.aka;
    }

    public void setName_en(String name_en) {
	this.name_en = name_en;
    }

    public String getName_en() {
	return this.name_en;
    }

    public void setBorn_place(String born_place) {
	this.born_place = born_place;
    }

    public String getBorn_place() {
	return this.born_place;
    }

    public void setAlt(String alt) {
	this.alt = alt;
    }

    public String getAlt() {
	return this.alt;
    }

}
