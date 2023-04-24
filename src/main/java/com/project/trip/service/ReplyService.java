package com.project.trip.service;

import com.project.trip.vo.Reply;

import java.util.List;

public interface ReplyService {
    public int insertReply(Reply r);

    public List<Reply> getAllReply(int boardNo);

    public int deleteReply(int replyId);

    public Reply getOneReply(int replyId);

    public int updateReply(Reply reply);
}
