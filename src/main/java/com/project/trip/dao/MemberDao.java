package com.project.trip.dao;

import com.project.trip.vo.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberDao {
    int insertMember(Member m);
    Member selectOneMember(String userId);
    int deleteMember(Member m);
    int updateMember(String userId, String encodedPassword);
    Member selectByName(String userNickname);
    Member selectByEmail(String email);

}
