package com.project.trip.dao;

import com.project.trip.vo.Reply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyDao {
    public int insertReply(Reply r);
    public List<Reply> getAllReply(int boardNo);
    public int deleteReply(int replyId);
    public Reply getOneReply(int replyId);
    public int updateReply(Reply reply);

}
