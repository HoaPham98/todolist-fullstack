package com.hiep.todolist.auth;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import com.hiep.todolist.entity.User;

public class BasicSecurityConext implements SecurityContext {

	private User user;
	private boolean secure;

	public BasicSecurityConext(User user, boolean secure) {
		super();
		this.user = user;
		this.secure = secure;
	}

	public Principal getUserPrincipal() {
		// TODO Auto-generated method stub
		return () -> user.getUserName();
	}

	public boolean isUserInRole(String role) {
		// TODO Auto-generated method stub
		return user.getRoles().contains(role);
	}

	public boolean isSecure() {
		// TODO Auto-generated method stub
		return secure;
	}

	public String getAuthenticationScheme() {
		// TODO Auto-generated method stub
		return SecurityContext.BASIC_AUTH;
	}

}
