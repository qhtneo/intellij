package com.project.trip.dao;

import com.project.trip.vo.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
    int insertMember(Member m);
    Member selectOneMember(String userId);
    int deleteMember(Member m);
    int updateMember(String userId, String encodedPassword);

}
