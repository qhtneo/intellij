package com.project.trip.service;

import com.project.trip.vo.Board;

import java.util.List;

public interface BoardService {

    public int write(Board board);

    public List<Board> selectAllBoard();

    public Board read(int boardNo);
}
