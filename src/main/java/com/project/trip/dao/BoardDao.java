package com.project.trip.dao;

import com.project.trip.vo.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardDao {
    public int writeBoard(Board board);

    public Board selectOneBoard(int boardNo);

    public int updateBoard(Board board);

    public int deleteBoard(int boardNo);

    public List<Board> selectAllBoard(RowBounds rb);
    public int countBoard();

    public List<Board> selectBoardByKeyword(Map<String, Object> map, RowBounds rb);
}
