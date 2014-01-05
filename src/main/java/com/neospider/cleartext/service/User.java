package com.neospider.cleartext.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.att.api.oauth.OAuthToken;

public class User {

	private String username;
	private String parentAddr;
	private String kidAddr;
	private String code;
	private OAuthToken token;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getParentAddr() {
		return parentAddr;
	}

	public void setParentAddr(String parentAddr) {
		this.parentAddr = parentAddr;
	}

	public String getKidAddr() {
		return kidAddr;
	}

	public void setKidAddr(String kidAddr) {
		this.kidAddr = kidAddr;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public OAuthToken getToken() {
		return token;
	}

	public void setToken(OAuthToken token) {
		this.token = token;
	}

	@Override
  public String toString() {
		return ToStringBuilder.reflectionToString(this);
  }

}
