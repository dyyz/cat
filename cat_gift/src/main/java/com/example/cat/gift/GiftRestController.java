package com.example.cat.gift;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GiftRestController {

	@GetMapping("index")
	public String index() {
		return "This is Index";
	}
	
}
