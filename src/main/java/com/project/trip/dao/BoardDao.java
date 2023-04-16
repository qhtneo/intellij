package com.project.trip.dao;

import com.project.trip.vo.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDao {
    public int insertBoard(Board board);

    public List<Board> selectAllBoard();

    public Board selectOneBoard(int boardNo);
}
