package com.x1.chan;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


import com.x1.chan.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

	private MemberService memberService;

	public HomeController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping(value = "/")
	public String home(Locale locale, Model model) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);

		return "member/memberForm";
	}
}
