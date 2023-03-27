package com.PostAndGetMicroService.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employeeinfo")
public class EmployeeInfo {
	
	@Id
	@GeneratedValue
	int eid;
	
	String ename;
	
	String edepartment;
	
	String username;
	
	String password;
	
	String roles;

}
