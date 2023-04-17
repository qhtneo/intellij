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


import java.util.List;

@Slf4j
@Controller
public class BoardController {

    @Autowired
    private BoardService bService;

    @Autowired
    private MemberService mService;

    @GetMapping("/board")
    public String board(Model model){
        List<Board> boardList = bService.selectAllBoard();
        log.debug("list size {}  ", boardList.size());

        model.addAttribute("boardList", boardList);

        return "board/boardList";
    }

    @GetMapping("/write")
    public String write(){
        return "board/write";
    }

    @PostMapping("/write")
    public String write(Board board, @AuthenticationPrincipal UserDetails user) {
        log.debug("write(Board)");
        log.debug("Board : {}", board);
        String userId = user.getUsername();
        Member member  = mService.selectOneMember(userId);
        board.setUserNickname((member.getUserNickname()));
        board.setUserNo(member.getUserNo());
        bService.write(board);
        return "redirect:/board";
    }

    @GetMapping("/read")
    public String read(Model model, int boardNo) {
        log.debug("read()");
        log.debug("boardNum : {}", boardNo);

        // service 호출
        // 글읽기와 조회수가 합쳐짐
        Board board = bService.read(boardNo);
        // model 객체에 글 정보 담기
        model.addAttribute("board", board);

        return "board/read";
    }
}
