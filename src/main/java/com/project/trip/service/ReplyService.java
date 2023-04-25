package com.project.trip.service;

import com.project.trip.vo.Reply;

import java.util.List;
import java.util.Map;

public interface ReplyService {
    public int insertReply(Reply reply);

    public List<Reply> getAllReply(int boardNo);

    public int deleteReply(int replyId);

    public Reply getOneReply(int replyId);

    public int updateReply(Reply reply);

    public List<Map<String, Object>> getMyReply(int userNo);
}
