package com.GetCallFromPostAndGetMicroServiceUsingWebClient.SecurityConfig;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.GetCallFromPostAndGetMicroServiceUsingWebClient.Entity.EmployeeInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeInfoToUserDetails implements UserDetails{
	
	String usernames;
	String passwords;
	
	List<GrantedAuthority> authorities;
	

	public EmployeeInfoToUserDetails(EmployeeInfo employeeInfo) {
		
		this.usernames = employeeInfo.getUsername();
		this.passwords = employeeInfo.getPassword();
		this.authorities = Arrays.stream(employeeInfo.getRoles().split(","))
				          .map(n->new SimpleGrantedAuthority(n))
				          .collect(Collectors.toList());
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return passwords;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return usernames;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}



}
