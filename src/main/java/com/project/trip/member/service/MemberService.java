package com.project.trip.member.service;

import com.project.trip.member.vo.Member;

import java.util.List;

public interface MemberService {
    public int insertMember(Member m);

    public Member selectOneMember(String id);

    int deleteMember(String userId);

    int updateMember(Member m);

    Member selectByName(String keyword);

    Member selectByEmail(String email);

    List<Member> selectAllMember();
}
