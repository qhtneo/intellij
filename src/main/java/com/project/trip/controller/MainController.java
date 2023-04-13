package com.project.trip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	//메인화면 띄우기
	@GetMapping({"", "/"}) //localhost8888 enter or localhost8888/ enter
	public String index() {
		return "main";
	}
}
