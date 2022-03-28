package com.x1.chan;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping(value = "/")
	public String home() {
		return "/index";
	}

	@GetMapping(value = "/interceptorTest")
	public String inter(){
		return "/index";
	}
}
