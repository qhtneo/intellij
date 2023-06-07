package com.project.trip.reply.service;

import com.project.trip.reply.dao.ReplyDao;
import com.project.trip.reply.vo.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyDao rDao;

    @Override
    public int insertReply(Reply reply) {
        return rDao.insertReply(reply);
    }

    @Override
    public List<Reply> getAllReply(int boardNo) {
        return rDao.getAllReply(boardNo);
    }

    @Override
    public int deleteReply(int replyId) {
        return rDao.deleteReply(replyId);
    }

    @Override
    public Reply getOneReply(int replyId) {
        return rDao.getOneReply(replyId);
    }

    @Override
    public int updateReply(Reply reply) {
        return rDao.updateReply(reply);
    }

    @Override
    public List<Map<String, Object>> getMyReply(int userNo) {
        return rDao.getMyReply(userNo); // 변경
    }
}

