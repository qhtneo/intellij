package com.project.trip.service;

import com.project.trip.dao.BoardDao;
import com.project.trip.vo.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardDao bDao;

    @Override
    public int writeBoard(Board board) {
        return bDao.writeBoard(board);
    }

    @Override
    public List<Board> selectAllBoard() {
        return bDao.selectAllBoard();
    }

    @Override
    public Board selectOneBoard(int boardNo) {
        return bDao.selectOneBoard(boardNo);
    }

    @Override
    public int updateBoard(Board board) {
        return bDao.updateBoard(board);
    }

    @Override
    public int deleteBoard(int boardNo) {
        return bDao.deleteBoard(boardNo);
    }
}
