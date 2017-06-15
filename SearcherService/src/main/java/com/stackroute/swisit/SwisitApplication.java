package com.stackroute.swisit;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.stackroute.swisit.domain.SwisitBean;


@SpringBootApplication
@EnableMongoRepositories
public class SwisitApplication {

	
	public static void main(String[] args) {
		
		
		ApplicationContext ctx = SpringApplication.run(SwisitApplication.class, args);
        SwisitBean sb = ctx.getBean(SwisitBean.class);
        System.out.println(sb.getTitle());
		
		
	}
		
	}

