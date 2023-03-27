package com.PostAndGetMicroService.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.PostAndGetMicroService.Entity.EmployeeInfo;
import com.PostAndGetMicroService.Repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public EmployeeInfo saveEmployeeInfo(EmployeeInfo employeeInfo) {
		
		employeeInfo.setPassword(passwordEncoder.encode(employeeInfo.getPassword()));
		
		return employeeRepository.save(employeeInfo);
	}

	@Override
	public List<EmployeeInfo> getEmployeeInfo() {
		
		List<EmployeeInfo> get = employeeRepository.findAll(Sort.by("roles"));
		
		return get;
	}

}
