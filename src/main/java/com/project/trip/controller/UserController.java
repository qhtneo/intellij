package com.project.trip.controller;

import com.project.trip.service.UserService;
import com.project.trip.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class UserController {
    @Autowired
    private UserService uService;
    private final String REDIRECT_INDEX = "redirect:/";

    @GetMapping("/join")
    public String join(){
        return "user/joinForm";
    }
    @PostMapping("/join")
    public String join(User user, String userId) {
        //Service 호출하기
        log.debug("join실행됨{}",user);
        uService.joinUser(user);
        return REDIRECT_INDEX;
    }
    @GetMapping("/login")
    public String login() {
        log.debug("login()");
        return "user/loginForm";
    }
}
