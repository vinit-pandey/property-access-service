package com.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservice.bean.PropertyAccessBean;
import com.microservice.bean.PropertyAccessValue;

@RestController
@RequestMapping("/access")

public class PropertyFileAccessController {
	
	@Autowired
	PropertyAccessBean propertyaccessbean;
	
	@GetMapping("accesspropertyfile")
	public PropertyAccessValue accesspropertyfile()
	{
		//refreshActuator();
		return new PropertyAccessValue(propertyaccessbean.getName(),propertyaccessbean.getDescription());
	}
	
	public void refreshActuator(){
        RestTemplate restTemplate=new RestTemplate();
        final String baseUrl="http://localhost:8100/actuator/refresh";

        HttpHeaders headers=new HttpHeaders();
        headers.set("Content-Type","application/json");
        HttpEntity entity=new HttpEntity(headers);

        ResponseEntity<String> results=restTemplate.postForEntity(baseUrl,entity,String.class);
	}

}
