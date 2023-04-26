package com.project.trip.service;

import com.project.trip.dao.BoardDao;
import com.project.trip.vo.Board;
import com.project.trip.vo.Member;
import com.project.trip.vo.PageNavigator;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public
class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardDao bDao;

    @Override
    public int writeBoard(Board board) {
        return bDao.writeBoard(board);
    }

    @Override
    public Board selectOneBoard(int boardNo)
    {
        bDao.addViewCount(boardNo);
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

    @Override
    public PageNavigator getPageNavigator(int pagePerGroup, int countPerPage, int page, String keyword, String category, String localCategory) {
        int total = bDao.countBoard(keyword, category, localCategory);

        PageNavigator navi = new PageNavigator(pagePerGroup, countPerPage, page, total);

        return navi;
    }

    @Override
    public List<Board> selectAllBoard(PageNavigator navi) {

        RowBounds rb = new RowBounds(navi.getStartRecord(), navi.getCountPerPage());

        return bDao.selectAllBoard(rb);
    }

    @Override
    public List<Board> selectBoardByKeyword(String localCategory, String keyword, String category, PageNavigator navi) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("category", category);
        map.put("keyword", keyword);
        map.put("localCategory", localCategory);
        RowBounds rb = new RowBounds(navi.getStartRecord(), navi.getCountPerPage());

        return bDao.selectBoardByKeyword(map, rb);
    }

    @Override
    public List<Board> selectBoardById(String userId) {return bDao.selectBoardById(userId); }
    @Override
    public List<Board> selectBoardByRecommend(String userId) {
        return bDao.selectBoardByRecommend(userId);
    }


    @Override
    public boolean recommend(int boardNo,String userId) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("boardNo",boardNo);
        map.put("userId", userId);
        int checkResult = bDao.checkRecommendHistory(map);
        //추천한 적 없음
        if(checkResult==0) {
            bDao.insertRecommendHistory(map);
            return true;
        }
        else{
            bDao.deleteRecommendHistory(map);
            return false;
        }
    }

    @Override
    public int updateRecommend(int boardNo) {
        bDao.updateRecommend(boardNo);
        Board b = bDao.selectOneBoard(boardNo);
        return b.getRecommend();

    }

    @Override
    public int deleteRecommend(int boardNo) {
        bDao.deleteRecommend(boardNo);
        Board b = bDao.selectOneBoard(boardNo);
        return b.getRecommend();
    }

    @Override
    public boolean checkRecommend(int boardNo, String userId) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("boardNo",boardNo);
        map.put("userId", userId);
        int checkResult = bDao.checkRecommendHistory(map);
        //추천한 적 없음
        if(checkResult==0) {
            return true;
        }
        else{
            return false;
        }
    }



}
