package com.greco.security;

import com.greco.model.Rol;
import java.util.List;
import com.greco.model.Users;

import java.util.Collection;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import java.util.ArrayList;

public class CrmUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Collection<? extends GrantedAuthority> authorities;
	private String username;
	private String password;


	public CrmUserDetails(Users user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		//this.authorities = translate(user.getRolId());
	}

	private Collection<? extends GrantedAuthority> translate(Rol role) {
		List<GrantedAuthority> authorities = new ArrayList<>();

		String name = role.getName().toUpperCase();
		if (!name.startsWith("ROLE_")) {
			name = "ROLE_" + name;
		}
		authorities.add(new SimpleGrantedAuthority(name));

		return authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
