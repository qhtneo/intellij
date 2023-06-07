package com.project.trip.reply.dao;

import com.project.trip.reply.vo.Reply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReplyDao {
    public int insertReply(Reply reply);
    public List<Reply> getAllReply(int boardNo);
    public int deleteReply(int replyId);
    public Reply getOneReply(int replyId);
    public int updateReply(Reply reply);
    List<Map<String, Object>> getMyReply(int userNo); // 변경

}
