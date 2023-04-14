package com.project.trip.service;

import com.project.trip.dao.MemberDao;
import com.project.trip.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberDao mDao;
    @Override
    public int joinMember(Member m) {
        String encodedPassword = passwordEncoder.encode(m.getPassword());
        //비번 새로 설정
        m.setUserPw(encodedPassword);
        return mDao.insertMember(m);
    }

    @Override
    public Member findOneMember(String id) {
        return mDao.selectOneMember(id);
    }

    @Override
    public int deleteMember(String userId) {
        Member m = new Member();
        m.setUserId(userId);
        return mDao.deleteMember(m);
    }
}
