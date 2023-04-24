package com.project.trip.service;

import com.project.trip.dao.ReplyDao;
import com.project.trip.vo.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService{
    @Autowired
    private ReplyDao rDao;

    @Override
    public int insertReply(Reply r){
        return rDao.insertReply(r);
    }

    @Override
    public List<Reply> getAllReply(int boardNo){
        return rDao.getAllReply(boardNo);
    }
    @Override
    public int deleteReply(int replyId) {
        return rDao.deleteReply(replyId);
    }

    @Override
    public Reply getOneReply(int replyId){
        return  rDao.getOneReply(replyId);
    }

    @Override
    public int updateReply(Reply reply){
        return rDao.updateReply(reply);
    }
}

