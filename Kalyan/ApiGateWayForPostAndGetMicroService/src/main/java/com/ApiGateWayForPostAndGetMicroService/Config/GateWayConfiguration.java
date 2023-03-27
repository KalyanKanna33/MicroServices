package com.ApiGateWayForPostAndGetMicroService.Config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfiguration {
	
	//Instead of Providing in application.properties we can provide routing here by RouteLocator
	
	
	public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
		
		return routeLocatorBuilder.routes()
				.route("PostAndGetMicroService", r->r.path("/EmployeeInfoPost", "/EmployeeInfoGet", "/GenerateToken", "/CheckEligibility")
						.and()
						.uri("lb://PostAndGetMicroService"))
				.build();
	}

}
