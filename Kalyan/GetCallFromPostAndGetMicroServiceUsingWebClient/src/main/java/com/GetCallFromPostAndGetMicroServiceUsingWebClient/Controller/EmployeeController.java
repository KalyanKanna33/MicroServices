package com.GetCallFromPostAndGetMicroServiceUsingWebClient.Controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.GetCallFromPostAndGetMicroServiceUsingWebClient.Entity.EmployeeInfo;

@RestController
@RequestMapping("/Webclient")
public class EmployeeController {
	
	@Autowired
	private WebClient webClient;
	
	
	//This EndPoint is Secured
	@GetMapping("/EmployeeInfoGetByWebclient")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<EmployeeInfo>> getEmployeeInfo() {
		
		EmployeeInfo[] get = webClient.get()
				             .uri("http://localhost:8081/EmployeeInfoGet")
				             .retrieve()
				             .bodyToMono(EmployeeInfo[].class)
				             .block();
		
		List<EmployeeInfo> l = Arrays.asList(get);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(l);
		
	}
	
	

}
