package com.project.trip.controller;

import com.project.trip.service.BoardService;
import com.project.trip.service.MemberService;
import com.project.trip.vo.Board;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    private MemberService mService;
    @Autowired
    private BoardService bService;
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
        mService.updateMember(member);
        return REDIRECT_INDEX;
    }
    //아이디 중복확인하는 로직
    @PostMapping("/checkId")
    @ResponseBody
    public String checkId(String id) {
        Member m = mService.selectOneMember(id);
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
        Member m = mService.selectByName(userNickname);
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
        Member m = mService.selectByEmail(email);
        //DB에서 id에 대한 검색결과가 없으면 ok 아니면 ng 이렇게 돌려준다
        if(m == null) {
            return "OK";
        }else {
            return "NG";
        }
    }
    @GetMapping("/myBoardList")
    public String myBoardList(@AuthenticationPrincipal UserDetails user, Model model){
        String userId = user.getUsername();
        Member member = mService.selectOneMember(userId);
        //클라이언트에 멤버 객체 전달
        model.addAttribute("member",member);
        List<Board> boardList = bService.selectBoardById(userId);
        model.addAttribute("boardList", boardList);
        return "member/myBoardList";
    }
    @PostMapping("/recommend")
    @ResponseBody
    public Map<String, Object> recommend(@AuthenticationPrincipal UserDetails user, int boardNo){

        String userId = user.getUsername();
        boolean result = bService.recommend(boardNo,userId);
        if(result) {
            int a = bService.updateRecommend(boardNo);
            Map<String, Object> map = new HashMap<>();
            map.put("value1", "OK");
            map.put("value2", a);
            return map;
        }
        else {;
            Map<String, Object> map = new HashMap<>();
            map.put("value1", "NG");
            return map;}
    }
}
