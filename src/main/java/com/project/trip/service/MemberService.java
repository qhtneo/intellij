package com.project.trip.service;

import com.project.trip.vo.Member;

public interface MemberService {
    public int joinMember(Member m);
    public Member findOneMember(String id);
    int deleteMember(String userId);
}
