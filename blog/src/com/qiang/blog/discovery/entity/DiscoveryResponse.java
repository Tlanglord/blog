package com.qiang.blog.discovery.entity;


public class DiscoveryResponse {
    private int code;

    private DiscoveryData data;

    public void setCode(int code) {
	this.code = code;
    }

    public int getCode() {
	return this.code;
    }

    public void setData(DiscoveryData data) {
	this.data = data;
    }

    public DiscoveryData getData() {
	return this.data;
    }
}