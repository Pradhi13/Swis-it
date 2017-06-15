package com.stackroute.swisit.apigateway;



import org.apache.catalina.connector.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.stackroute.swisit.filters.pre.PreFilter;


@SpringBootApplication
@EnableZuulProxy
public class ApigatewayApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}
	
	 @Bean
	  public PreFilter preFilter() {
	    return new PreFilter();
	  }


	
}
