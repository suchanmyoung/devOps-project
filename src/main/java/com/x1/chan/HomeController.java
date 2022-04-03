package com.x1.chan;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;

@Controller
public class HomeController {

	@GetMapping(value = "/")
	public String home()
	{

		return "/index";
	}

}
