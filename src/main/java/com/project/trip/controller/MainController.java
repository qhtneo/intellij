package com.project.trip.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class MainController {

	//메인화면 띄우기
//	@GetMapping({"", "/"}) //localhost8888 enter or localhost8888/ enter
//	public String index() {
//		return "main";
//	}

	@GetMapping({"", "/"}) //localhost8888 enter or localhost8888/ enter
	public String index(Model model) {
		String address = "광주광역시 광산구 무진대로 282";
		model.addAttribute("address", address);
		return "main";
	}

	@PostMapping("/main1")
	@ResponseBody
	public String main(String address1,Model model){
		log.debug(address1);
		model.addAttribute("address1", address1);
		return "main";
	}
}
