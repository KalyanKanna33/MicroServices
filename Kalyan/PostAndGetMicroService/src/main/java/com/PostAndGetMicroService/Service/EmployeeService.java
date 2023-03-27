package com.PostAndGetMicroService.Service;

import java.util.List;

import com.PostAndGetMicroService.Entity.EmployeeInfo;

public interface EmployeeService {
	
	
	public EmployeeInfo saveEmployeeInfo(EmployeeInfo employeeInfo);
	
	
	public List<EmployeeInfo> getEmployeeInfo();

}
