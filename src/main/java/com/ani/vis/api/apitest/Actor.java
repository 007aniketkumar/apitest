package com.ani.vis.api.apitest;

public class Actor {


	public String getactorID() {
		return actorID;
	}
	public void setactorID(String actorID) {
		this.actorID = actorID;
	}
	public int getLogin() {
		return login;
	}
	public void setLogin(int login) {
		this.login = login;
	}
	public String getAvatar_url() {
		return avatar_url;
	}
	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}
	private String actorID;
	
	private int login;
	private String avatar_url;
	
}
