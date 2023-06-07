package com.project.trip.board.api;

import com.project.trip.board.vo.Board;
import com.project.trip.board.service.BoardService;
import com.project.trip.member.service.MemberService;
import com.project.trip.reply.service.ReplyService;
import com.project.trip.member.vo.Member;
import com.project.trip.common.vo.PageNavigator;
import com.project.trip.reply.vo.Reply;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardCommandController {

    private final BoardService boardService;
    private final MemberService memberService;
    private final ReplyService replyService;

    // 게시판 목록의 페이지당 글 수
    @Value("${user.board.page}")
    int countPerPage;

    // 게시판 목록의 페이지 이동 링크 수
    @Value("${user.board.group}")
    int pagePerGroup;


    // 게시판 글쓰기 페이지로 이동
    @GetMapping("/writeBoard")
    public String writeBoard() {
        return "board/writeBoard";
    }

    // 게시판 글쓰기 완료 목록으로 되돌아가기
    @PostMapping("/writeBoard")
    public String writeBoard(Board board, @AuthenticationPrincipal UserDetails user) {
        boardService.writeBoard(board,user);
        return "redirect:/board";
    }

    // 게시판 글 상세보기
    @GetMapping("/readBoard")
    public String readBoard(Model model, int boardNo) {
        // service 호출
        Board board = boardService.selectOneBoard(boardNo);

        // model 객체에 글 정보 담기
        model.addAttribute("board", board);

        return "board/readBoard";
    }

    // 게시판 글 수정하기 페이지로 이동
    @GetMapping("/updateBoard")
    public String updateBoard(Model model, int boardNo) {
        // service 호출
        Board board = boardService.selectOneBoard(boardNo);

        // model 객체에 글 정보 담기
        model.addAttribute("board", board);
        log.debug("boardIS:{}", board);
        return "board/updateBoard";
    }

    // 게시판 글 수정 후 글 상세보기 페이지로 이동
    @PostMapping("/updateBoard")
    public String updateBoard(Board board, @AuthenticationPrincipal UserDetails user, Model model) {
        String userId = user.getUsername();
        log.debug("boardOb: {}",board);
        boardService.updateBoard(board);
        Member member = memberService.selectOneMember(userId);
        log.debug("member:{}",member);
        //클라이언트에 멤버 객체 전달
        model.addAttribute("member", member);
        List<Board> boardList = boardService.selectBoardById(userId);
        model.addAttribute("boardList", boardList);
        return "member/myBoardList";
    }

    // 게시판 글 수정
    @GetMapping("/deleteBoard")
    public String deleteBoard(int boardNo) {
        boardService.deleteBoard(boardNo);

        return "redirect:/myBoardList";
    }

    // 글 검색
    @GetMapping("/board")
    public String searchBoard(String localCategory,String category, String keyword, Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
        if(localCategory == null){
            localCategory = "전체";
        }

        PageNavigator navi = boardService.getPageNavigator(pagePerGroup, countPerPage, page, keyword, category, localCategory);

        List<Board> boardList = boardService.selectBoardByKeyword(localCategory,keyword, category, navi);
        log.debug("search activate :{}", boardList.size());

        model.addAttribute("navi", navi);
        model.addAttribute("boardList", boardList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("category", category);
        model.addAttribute("localCategory", localCategory);

        return "board/boardList";
    }

    // 댓글 쓰기
    @PostMapping("/insertReply")
    @ResponseBody
    public String insertReply(int boardNo, String replyContent, @AuthenticationPrincipal UserDetails user) {
        String userId = user.getUsername(); // userNIckname
        Member member = memberService.selectOneMember(userId); // user 정보 검색
        int userNo = member.getUserNo();
        String userNickname = member.getUserNickname();

        Reply r = new Reply();
        r.setBoardNo(boardNo);
        r.setUserNo(userNo);
        r.setUserNickname(userNickname);
        r.setReplyContent(replyContent);
        r.setBoardNo(boardNo);
        replyService.insertReply(r);
        return "OK";
    }
    
    // 댓글 목록
    @PostMapping("/loadReply")
    @ResponseBody
    public Map<String, Object> loadReply(int boardNo, @AuthenticationPrincipal UserDetails user) {
        List<Reply> replyList = replyService.getAllReply(boardNo); // 게시글에 있는 댓글 전체
        Map<String, Object> map = new HashMap<>();
        map.put("replyList", replyList); // 댓글 목록

        if(user != null){ // 로그인 했을 때
            String userId = user.getUsername();
            Member member = memberService.selectOneMember(userId);
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
        replyService.deleteReply(replyId);
        return "OK";
    }

    // 댓글 수정하기 위해 하나의 댓글 가져오기
    @PostMapping("/getOneReply")
    @ResponseBody
    public Reply getOneReply(int replyId) {
        Reply reply = replyService.getOneReply(replyId);
        return reply;
    }

    // 댓글 수정
    @PostMapping("/updateReply")
    @ResponseBody
    public String updateReply(String replyContent, int replyId) {
        Reply reply = new Reply();
        reply.setReplyContent(replyContent);
        reply.setReplyId(replyId);

        replyService.updateReply(reply);

        return "OK";
    }
}
