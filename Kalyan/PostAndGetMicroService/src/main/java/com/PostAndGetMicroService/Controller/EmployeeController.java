package com.PostAndGetMicroService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.PostAndGetMicroService.Entity.EmployeeInfo;
import com.PostAndGetMicroService.Entity.JwtAuthenticationRequest;
import com.PostAndGetMicroService.JwtToken.JwtTokenGenerateAndValidate;
import com.PostAndGetMicroService.Service.EmployeeServiceImpl;

@RestController
public class EmployeeController {
	
	
	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;
	
	@Autowired
	private JwtTokenGenerateAndValidate jwtTokenGenerateAndValidate;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@PostMapping("/EmployeeInfoPost")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public String saveEmployeeInfo(@RequestBody EmployeeInfo employeeInfo) {
		
		employeeServiceImpl.saveEmployeeInfo(employeeInfo);
		
		return "Successfully Inserted: ";
		
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/EmployeeInfoGet")
	public ResponseEntity<List<EmployeeInfo>> getEmployee() {
		
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeServiceImpl.getEmployeeInfo());
		
	}
	
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@GetMapping("/CheckEligibility")
	public ResponseEntity<String> getMessage() {
		
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("All Authorized Persons: ");
		
	}
	
	
	@PostMapping("/GenerateToken")
	public ResponseEntity<String> authenticateAndGetToken( @RequestBody JwtAuthenticationRequest jwtAuthenticationRequest) {
		
	Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtAuthenticationRequest.getUsername(), jwtAuthenticationRequest.getPassword()));
		
	if(auth.isAuthenticated()) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(jwtTokenGenerateAndValidate.generateToken(jwtAuthenticationRequest.getUsername()));
		
	}else {
		throw new UsernameNotFoundException("Invalid User Request, User Not Found");
	}

	}
}
