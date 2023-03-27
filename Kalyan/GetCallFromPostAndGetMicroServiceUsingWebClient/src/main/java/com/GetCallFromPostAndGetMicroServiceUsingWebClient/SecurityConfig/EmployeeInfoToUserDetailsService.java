package com.GetCallFromPostAndGetMicroServiceUsingWebClient.SecurityConfig;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.GetCallFromPostAndGetMicroServiceUsingWebClient.Entity.EmployeeInfo;
import com.GetCallFromPostAndGetMicroServiceUsingWebClient.Repository.EmployeeRepository;

public class EmployeeInfoToUserDetailsService implements UserDetailsService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
	Optional<EmployeeInfo> employeeCredentials  = employeeRepository.findByUsername(username);
		
		
		return employeeCredentials.map(n-> new EmployeeInfoToUserDetails(n))
				                      .orElseThrow(()-> new UsernameNotFoundException("User Not Fount: "));
	}

}
