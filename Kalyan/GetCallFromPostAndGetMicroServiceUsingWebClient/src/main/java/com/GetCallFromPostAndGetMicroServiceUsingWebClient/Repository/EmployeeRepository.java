package com.GetCallFromPostAndGetMicroServiceUsingWebClient.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.GetCallFromPostAndGetMicroServiceUsingWebClient.Entity.EmployeeInfo;

@Component
public interface EmployeeRepository extends JpaRepository<EmployeeInfo, Integer>{
	
	Optional<EmployeeInfo> findByUsername(String username);

}
