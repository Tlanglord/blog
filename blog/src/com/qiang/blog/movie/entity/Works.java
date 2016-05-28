package com.qiang.blog.movie.entity;

import java.util.List;



public class Works {
    private List<String> roles;

    private Subject subject;

    public void setRoles(List<String> roles) {
	this.roles = roles;
    }

    public List<String> getRoles() {
	return this.roles;
    }

    public void setSubject(Subject subject) {
	this.subject = subject;
    }

    public Subject getSubject() {
	return this.subject;
    }

}
