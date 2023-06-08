package com.project.trip.controller;

import com.project.trip.service.MemberService;
import com.project.trip.service.ReplyService;
import com.project.trip.vo.Member;
import com.project.trip.vo.Reply;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ReplyController {
    private final MemberService mService;
    private final ReplyService rService;

    // 댓글 쓰기
    @PostMapping("/insertReply")
    @ResponseBody
    public String insertReply(int boardNo, String replyContent, @AuthenticationPrincipal UserDetails user) {
        String userId = user.getUsername(); // userNickName
        Member member = mService.selectOneMember(userId); // user 정보 검색
        int userNo = member.getUserNo();
        String userNickname = member.getUserNickname();

        Reply r = new Reply();
        r.setBoardNo(boardNo);
        r.setUserNo(userNo);
        r.setUserNickname(userNickname);
        r.setReplyContent(replyContent);
        r.setBoardNo(boardNo);
        rService.insertReply(r);
        return "OK";
    }

    // 댓글 목록
    @PostMapping("/loadReply")
    @ResponseBody
    public Map<String, Object> loadReply(int boardNo, @AuthenticationPrincipal UserDetails user) {
        List<Reply> replyList = rService.getAllReply(boardNo); // 게시글에 있는 댓글 전체
        Map<String, Object> map = new HashMap<>();
        map.put("replyList", replyList); // 댓글 목록

        if(user != null){ // 로그인 했을 때
            String userId = user.getUsername();
            Member member = mService.selectOneMember(userId);
            map.put("userNo", member.getUserNo());
        } else { // 비로그인
            map.put("userNo", null);
            map.put("userNickname", null);
        }
        return map;
    }

    // 댓글 삭제
    @GetMapping("/deleteReply")
    @ResponseBody
    public String deleteReply(int replyId) {
        log.debug("replyId : {}", replyId );
        rService.deleteReply(replyId);
        return "OK";
    }

    // 댓글 수정하기 위해 하나의 댓글 가져오기
    @PostMapping("/getOneReply")
    @ResponseBody
    public Reply getOneReply(int replyId) {
        Reply reply = rService.getOneReply(replyId);
        return reply;
    }

    // 댓글 수정
    @PostMapping("/updateReply")
    @ResponseBody
    public String updateReply(String replyContent, int replyId) {
        Reply reply = new Reply();
        reply.setReplyContent(replyContent);
        reply.setReplyId(replyId);

        rService.updateReply(reply);

        return "OK";
    }

}
