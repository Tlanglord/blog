package com.qiang.blog.discovery.entity;

import java.util.List;

public class DiscoveryData {
    private String channel;

    private List<DiscoveryArticle> article;

    public void setChannel(String channel) {
	this.channel = channel;
    }

    public String getChannel() {
	return this.channel;
    }

    public void setArticle(List<DiscoveryArticle> article) {
	this.article = article;
    }

    public List<DiscoveryArticle> getArticle() {
	return this.article;
    }

}