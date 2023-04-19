package com.project.trip.service;

import com.project.trip.vo.Board;
import com.project.trip.vo.Member;
import com.project.trip.vo.PageNavigator;

import java.util.List;

public interface BoardService {

    public int writeBoard(Board board);

    public Board selectOneBoard(int boardNo);

    public int updateBoard(Board board);

    public int deleteBoard(int boardNo);

    public PageNavigator getPageNavigator(int pagePerGroup, int countPerPage, int page);

    public List<Board> selectAllBoard(PageNavigator navi);

    public List<Board> selectBoardByKeyword(String keyword, String category, PageNavigator navi);

    public List<Board> selectBoardById(String userId);

    boolean recommend(int boardNo,String userId);

    int updateRecommend(int boardNo);

    String selectIdByBoard(String userId);
}
