package com.example.cat.user.entity;

import java.util.Collection;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class BaseUserDetail implements UserDetails, CredentialsContainer {

	private static final long serialVersionUID = -8179675331830185705L;

	private final UserLogin userLogin;
	private final User user;

	public BaseUserDetail(UserLogin userLogin, User user) {
		this.userLogin = userLogin;
		this.user = user;
	}

	@Override
	public void eraseCredentials() {
		user.eraseCredentials();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getAuthorities();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return user.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return isEnabled();
	}

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public User getUser() {
		return user;
	}
	
}
