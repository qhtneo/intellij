package com.project.trip.board.service;

import com.project.trip.board.vo.Board;
import com.project.trip.member.service.MemberService;
import com.project.trip.board.dao.BoardDao;
import com.project.trip.member.vo.Member;
import com.project.trip.common.vo.PageNavigator;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor

public class BoardServiceImpl implements BoardService {

    private final MemberService memberService;
    private final BoardDao boardDao;

    @Override
    public int writeBoard(Board board, UserDetails user) {
//        "redirect:/board"
        // 로그인 된 정보에서 userId 가져오기
        String userId = user.getUsername();

        Member member = memberService.selectOneMember(userId);

        board.setUserNickname(member.getUserNickname());
        board.setUserNo(member.getUserNo());
        return boardDao.writeBoard(board);
    }

    @Override
    public Board selectOneBoard(int boardNo)
    {
        boardDao.addViewCount(boardNo);
        return boardDao.selectOneBoard(boardNo);
    }

    @Override
    public int updateBoard(Board board) {
        return boardDao.updateBoard(board);
    }

    @Override
    public int deleteBoard(int boardNo) {
        return boardDao.deleteBoard(boardNo);
    }

    @Override
    public PageNavigator getPageNavigator(int pagePerGroup, int countPerPage, int page, String keyword, String category, String localCategory) {
        int total = boardDao.countBoard(keyword, category, localCategory);

        PageNavigator navi = new PageNavigator(pagePerGroup, countPerPage, page, total);

        return navi;
    }

//    @Override
//    public List<Board> selectAllBoard(PageNavigator navi) {
//
//        RowBounds rb = new RowBounds(navi.getStartRecord(), navi.getCountPerPage());
//
//        return bDao.selectAllBoard(rb);
//    }

    @Override
    public List<Board> selectAllBoard() {
        return boardDao.selectAllBoard();
    }

    @Override
    public List<Board> selectBoardByKeyword(String localCategory, String keyword, String category, PageNavigator navi) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("category", category);
        map.put("keyword", keyword);
        map.put("localCategory", localCategory);
        RowBounds rb = new RowBounds(navi.getStartRecord(), navi.getCountPerPage());

        return boardDao.selectBoardByKeyword(map, rb);
    }

    @Override
    public List<Board> selectBoardById(String userId) {return boardDao.selectBoardById(userId); }
    @Override
    public List<Board> selectBoardByRecommend(String userId) {
        return boardDao.selectBoardByRecommend(userId);
    }


    @Override
    public boolean recommend(int boardNo,String userId) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("boardNo",boardNo);
        map.put("userId", userId);
        int checkResult = boardDao.checkRecommendHistory(map);
        //추천한 적 없음
        if(checkResult==0) {
            boardDao.insertRecommendHistory(map);
            return true;
        }
        else{
            boardDao.deleteRecommendHistory(map);
            return false;
        }
    }

    @Override
    public int updateRecommend(int boardNo) {
        boardDao.updateRecommend(boardNo);
        Board b = boardDao.selectOneBoard(boardNo);
        return b.getRecommend();

    }

    @Override
    public int deleteRecommend(int boardNo) {
        boardDao.deleteRecommend(boardNo);
        Board b = boardDao.selectOneBoard(boardNo);
        return b.getRecommend();
    }

    @Override
    public boolean checkRecommend(int boardNo, String userId) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("boardNo",boardNo);
        map.put("userId", userId);
        int checkResult = boardDao.checkRecommendHistory(map);
        //추천한 적 없음
        if(checkResult==0) {
            return true;
        }
        else{
            return false;
        }
    }



}
