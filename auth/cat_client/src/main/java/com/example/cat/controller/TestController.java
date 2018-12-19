package com.example.cat.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TestController {

	@GetMapping("index")
	public String index() {
		return "THIS IS CLIENT INDEX PAGE";
	}
	
	@GetMapping("test")
	public String test(HttpServletRequest request) {
		log.info("=======================START=======================");
		Enumeration heardNames = request.getHeaderNames();
		while (heardNames.hasMoreElements()) {

			String key = (String) heardNames.nextElement();
			log.info(key + ":  " + request.getHeader(key));
		}
		log.info("=======================END=======================");
		return "THIS IS CLIENT INDEX PAGE";
	}
}
