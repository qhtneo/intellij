package com.project.trip.dao;

import com.project.trip.vo.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardDao {
    public int writeBoard(Board board);

    public List<Board> selectAllBoard();

    public Board selectOneBoard(int boardNo);

    public int updateBoard(Board board);

    public int deleteBoard(int boardNo);

    List<Board> selectBoardById(String userId);


    void updateRecommend(int boardNo);

    int checkRecommendHistory(Map<String, Object> map);

    void insertRecommendHistory(Map<String, Object> map);
}
