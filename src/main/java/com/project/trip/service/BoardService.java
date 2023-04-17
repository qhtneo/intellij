package com.project.trip.service;

import com.project.trip.vo.Board;

import java.util.List;

public interface BoardService {

    public int writeBoard(Board board);

    public List<Board> selectAllBoard();

    public Board selectOneBoard(int boardNo);

    public int updateBoard(Board board);

    public int deleteBoard(int boardNo);
}
