package com.project.trip.dao;

import com.project.trip.vo.Board;
import com.project.trip.vo.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardDao {
    int writeBoard(Board board);
    Board selectOneBoard(int boardNo);
    int updateBoard(Board board);
    int deleteBoard(int boardNo);
//    List<Board> selectAllBoard(RowBounds rb);
//    int countBoard(String keyword, String category, String localCategory);
    int countBoard(@Param("keyword") String keyword, @Param("category") String category, @Param("localCategory") String localCategory);

    List<Board> selectBoardByKeyword(Map<String, Object> map, RowBounds rb);
    List<Board> selectBoardById(String userId);
    void updateRecommend(int boardNo);
    int checkRecommendHistory(Map<String, Object> map);
    void insertRecommendHistory(Map<String, Object> map);
    void deleteRecommendHistory(Map<String, Object> map);

    void deleteRecommend(int boardNo);

    List<Board> selectBoardByRecommend(String userId);

    void addViewCount(int boardNo);

    List<Board> selectAllBoard();
}
