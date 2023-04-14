package com.project.trip.controller;

import com.project.trip.service.MemberService;
import com.project.trip.vo.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    private MemberService mService;
    private final String REDIRECT_INDEX = "redirect:/";

    @GetMapping("/join")
    public String join(){
        return "member/joinForm";
    }
    @PostMapping("/join")
    public String join(Member member, String userId) {
        //Service 호출하기
        log.debug("join실행됨{}",member);
        mService.joinMember(member);
        return REDIRECT_INDEX;
    }
    @GetMapping("/login")
    public String login() {
        log.debug("login()");
        return "member/loginForm";
    }
//    @GetMapping("/delete")
//    public String delete(){
//        return "member/deleteForm";
//    }
    @GetMapping("/delete")
    public String deleteMember(@AuthenticationPrincipal UserDetails user) {
        String userId = user.getUsername();
        int result = mService.deleteMember(userId);
        return "redirect:/logout";
    }
    @GetMapping("/myPage")
    public String myPage(@AuthenticationPrincipal UserDetails user, Model model){

        String userId = user.getUsername();
        log.debug("user id :{}",userId);

        Member member = mService.findOneMember(userId);
        System.out.println(member);
        //클라이언트에 멤버 객체 전달
        model.addAttribute("member",member);
        return "member/myPage";
    }
}
