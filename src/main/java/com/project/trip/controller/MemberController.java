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
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
        mService.insertMember(member);
        return REDIRECT_INDEX;
    }
    @GetMapping("/login")
    public String login() {
        log.debug("login()");
        return "member/loginForm";
    }

    @GetMapping("/deleteMember")
    public String deleteMember(@AuthenticationPrincipal UserDetails user) {
        String userId = user.getUsername();
        int result = mService.deleteMember(userId);
        return "redirect:/logout";
    }
    @GetMapping("/myPage")
    public String myPage(@AuthenticationPrincipal UserDetails user, Model model){

        String userId = user.getUsername();
        Member member = mService.selectOneMember(userId);
        //클라이언트에 멤버 객체 전달
        model.addAttribute("member",member);
        return "member/myPage";
    }
    @PostMapping("/updateMember")
    public String update(Member member){
        log.debug("membr:{}",member);
        mService.updateMember(member);
        return REDIRECT_INDEX;
    }
    //아이디 중복확인하는 로직
    @PostMapping("/checkId")
    @ResponseBody
    public String checkId(String id) {
        log.debug("id : {}",id);
        Member m = mService.selectOneMember(id);
        log.debug("m : {}",m);
        //DB에서 id에 대한 검색결과가 없으면 ok 아니면 ng 이렇게 돌려준다
        if(m == null) {
            return "OK";
        }else {
            return "NG";
        }
    }

    @PostMapping("/checkName")
    @ResponseBody
    public String checkName(String userNickname) {
        log.debug("userNickname :{}",userNickname);
        Member m = mService.selectByName(userNickname);
        log.debug("m : {}",m);
        //DB에서 id에 대한 검색결과가 없으면 ok 아니면 ng 이렇게 돌려준다
        if(m == null) {
            return "OK";
        }else {
            return "NG";
        }
    }
    @PostMapping("/checkEmail")
    @ResponseBody
    public String checkEmail(String email) {
        log.debug("userEmail :{}",email);
        Member m = mService.selectByEmail(email);
        log.debug("m : {}",m);
        //DB에서 id에 대한 검색결과가 없으면 ok 아니면 ng 이렇게 돌려준다
        if(m == null) {
            return "OK";
        }else {
            return "NG";
        }
    }
}
