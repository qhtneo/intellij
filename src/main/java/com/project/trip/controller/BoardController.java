package com.project.trip.controller;

import com.project.trip.service.BoardService;
import com.project.trip.service.MemberService;
import com.project.trip.vo.Board;
import com.project.trip.vo.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    // 게시판 목록의 페이지당 글 수
    // annotation을 가져오는 곳이 lombok이 아닌것에 주! 의!
    @Value("${user.board.page}")
    int countPerPage;

    // 게시판 목록의 페이지 이동 링크 수
    @Value("${user.board.group}")
    int pagePerGroup;




    @GetMapping("/board")
    public String board(Model model){
        List<Board> boardList = bService.selectAllBoard();
        log.debug("list size {}  ", boardList.size());

        model.addAttribute("boardList", boardList);

        return "board/boardList";
    }

    @GetMapping("/writeBoard")
    public String writeBoard(){
        return "board/writeBoard";
    }

    @PostMapping("/writeBoard")
    public String writeBoard(Board board, @AuthenticationPrincipal UserDetails user) {
        log.debug("write(Board)");
        log.debug("Board : {}", board);
        String userId = user.getUsername();
        Member member  = mService.findOneMember(userId);
        board.setUserNickname((member.getUserNickname()));
        board.setUserNo(member.getUserNo());
        bService.writeBoard(board);
        return "redirect:/board";
    }

    @GetMapping("/readBoard")
    public String readBoard(Model model, int boardNo) {
        log.debug("read()");
        log.debug("boardNum : {}", boardNo);

        // service 호출
        Board board = bService.selectOneBoard(boardNo);
        // model 객체에 글 정보 담기
        model.addAttribute("board", board);

        return "board/readBoard";
    }

    @GetMapping("/updateBoard")
    public String updateBoard(Model model, int boardNo){
        log.debug("update()");
        log.debug("boardNo : {}", boardNo);

        // service 호출
        Board board = bService.selectOneBoard(boardNo);

        // model 객체에 글 정보 담기
        model.addAttribute("board", board);

        return "board/updateBoard";
    }

    @PostMapping("/updateBoard")
    public String updateBoard(Board board){
        bService.updateBoard(board);

        return "redirect:/readBoard/?boardNo=" + board.getBoardNo();
    }

    @GetMapping("/deleteBoard")
    public String deleteBoard(int boardNo){
        bService.deleteBoard(boardNo);

        return "redirect:/board";
    }
}
