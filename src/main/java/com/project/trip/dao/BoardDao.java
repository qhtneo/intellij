package com.project.trip.dao;

import com.project.trip.vo.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDao {
    public int writeBoard(Board board);

    public List<Board> selectAllBoard();

    public Board selectOneBoard(int boardNo);

    public int updateBoard(Board board);

    public int deleteBoard(int boardNo);
}
