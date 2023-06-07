package com.project.trip.controller;

import com.project.trip.service.BoardService;
import com.project.trip.service.MemberService;
import com.project.trip.service.ReplyService;
import com.project.trip.vo.Board;
import com.project.trip.vo.Member;
import com.project.trip.vo.PageNavigator;
import com.project.trip.vo.Reply;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService bService;

    private final MemberService mService;

    // 게시판 목록의 페이지당 글 수
    @Value("${user.board.page}")
    int countPerPage;

    // 게시판 목록의 페이지 이동 링크 수
    @Value("${user.board.group}")
    int pagePerGroup;

    // 게시판 목록
//    @GetMapping("/board")
//
//    public String board(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
//        PageNavigator navi = bService.getPageNavigator(pagePerGroup, countPerPage, page);
//        log.debug(navi.toString());
//        List<Board> boardList = bService.selectAllBoard(navi);
//
//
//        model.addAttribute("navi", navi);
//        model.addAttribute("boardList", boardList);
//        return "board/boardList";
//    }

    // 게시판 글쓰기 페이지로 이동
    @GetMapping("/writeBoard")
    public String writeBoard() {
        return "board/writeBoard";
    }

    // 게시판 글쓰기 완료 목록으로 되돌아가기
    @PostMapping("/writeBoard")
    public String writeBoard(Board board, @AuthenticationPrincipal UserDetails user) {
        log.debug("write(Board)");
        log.debug("Board : {}", board);

        // 로그인 된 정보에서 userId 가져오기
        String userId = user.getUsername();
        Member member = mService.selectOneMember(userId);
        board.setUserNickname((member.getUserNickname()));
        board.setUserNo(member.getUserNo());

        bService.writeBoard(board);

        return "redirect:/board";
    }

    // 게시판 글 상세보기
    @GetMapping("/readBoard")
    public String readBoard(Model model, int boardNo) {
        // service 호출
        Board board = bService.selectOneBoard(boardNo);

        // model 객체에 글 정보 담기
        model.addAttribute("board", board);

        return "board/readBoard";
    }

    // 게시판 글 수정하기 페이지로 이동
    @GetMapping("/updateBoard")
    public String updateBoard(Model model, int boardNo) {
        // service 호출
        Board board = bService.selectOneBoard(boardNo);

        // model 객체에 글 정보 담기
        model.addAttribute("board", board);
        log.debug("boardIS:{}", board);

        return "board/updateBoard";
    }

    // 게시판 글 수정 후 글 상세보기 페이지로 이동
    @PostMapping("/updateBoard")
    public String updateBoard(Board board, @AuthenticationPrincipal UserDetails user, Model model) {

        String userId = user.getUsername();
        String category = board.getLocalCategory();
        board.setLocalCategory(category);
        bService.updateBoard(board);
        Member member = mService.selectOneMember(userId);
        //클라이언트에 멤버 객체 전달
        model.addAttribute("member", member);
        List<Board> boardList = bService.selectBoardById(userId);
        model.addAttribute("boardList", boardList);

        log.debug(category);

        return "member/myBoardList";
    }

    // 게시판 글 수정
    @GetMapping("/deleteBoard")
    public String deleteBoard(int boardNo) {
        bService.deleteBoard(boardNo);

        return "redirect:/myBoardList";
    }

    // 글 검색
    @GetMapping("/board")
    public String searchBoard(String localCategory,String category, String keyword, Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
        if(localCategory == null){
            localCategory = "전체";
        }

        PageNavigator navi = bService.getPageNavigator(pagePerGroup, countPerPage, page, keyword, category, localCategory);

        List<Board> boardList = bService.selectBoardByKeyword(localCategory,keyword, category, navi);
        log.debug("search activate :{}", boardList.size());

        model.addAttribute("navi", navi);
        model.addAttribute("boardList", boardList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("category", category);
        model.addAttribute("localCategory", localCategory);

        return "board/boardList";
    }
}
