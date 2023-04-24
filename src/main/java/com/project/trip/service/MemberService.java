package com.project.trip.service;

import com.project.trip.vo.Member;

import java.util.List;

public interface MemberService {
    public int insertMember(Member m);

    public Member selectOneMember(String id);

    int deleteMember(String userId);

    int updateMember(Member m);

    Member selectByName(String keyword);

    Member selectByEmail(String email);

}
