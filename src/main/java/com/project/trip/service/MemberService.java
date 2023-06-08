package com.project.trip.service;

import com.project.trip.vo.Member;

import java.util.List;
import java.util.Map;

public interface MemberService {
    public int insertMember(Member m);

    public Member selectOneMember(String id);

    int deleteMember(String userId);

    int updateMember(Member m);

    Member selectByName(String keyword);

    Member selectByEmail(String email);

    List<Member> selectAllMember();


    void updateRole(String userId, boolean enabled);
}
