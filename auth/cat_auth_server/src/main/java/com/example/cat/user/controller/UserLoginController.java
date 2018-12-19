package com.example.cat.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.cat.user.entity.UserLogin;
import com.example.cat.user.service.UserLoginService;

@Controller
public class UserLoginController {

	@Autowired
	@Qualifier("userLoginDetailService")
	UserLoginService userLoginService;
	
	@GetMapping({"/", "/index"})
	public String index() {
		return "index";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@PostMapping("/register")
	public String registerP(UserLogin userLogin) {
		userLoginService.registryUser(userLogin);
		return "login";
	}
}
