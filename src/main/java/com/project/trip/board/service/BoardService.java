package com.project.trip.board.service;

import com.project.trip.board.vo.Board;
import com.project.trip.common.vo.PageNavigator;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface BoardService {

    public int writeBoard(Board board, UserDetails user);

    public Board selectOneBoard(int boardNo);

    public int updateBoard(Board board);

    public int deleteBoard(int boardNo);

    public PageNavigator getPageNavigator(int pagePerGroup, int countPerPage, int page, String keyword, String category, String localCategory);

//    public List<Board> selectAllBoard(PageNavigator navi);
    public List<Board> selectAllBoard();

    public List<Board> selectBoardByKeyword(String localCategory, String keyword, String category, PageNavigator navi);

    public List<Board> selectBoardById(String userId);

    public boolean recommend(int boardNo,String userId);

    public int updateRecommend(int boardNo);


    int deleteRecommend(int boardNo);

    boolean checkRecommend(int boardNo, String userId);

    List<Board> selectBoardByRecommend(String userId);
}
