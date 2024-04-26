package com.appssmartminds.app.ws;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.appssmartminds.app.ws.ui.model.response.UserRest;

@SpringBootApplication
public class WebAiWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebAiWsApplication.class, args);
	}
	
	 @Bean
	 Map<String, UserRest> users() {
	 return new HashMap<String, UserRest>();
	 }

}
