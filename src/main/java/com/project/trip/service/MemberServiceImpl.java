package com.project.trip.service;

import com.project.trip.dao.MemberDao;
import com.project.trip.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberDao mDao;
    @Override
    public int insertMember(Member m) {
        String encodedPassword = passwordEncoder.encode(m.getPassword());
        //비번 새로 설정
        m.setUserPw(encodedPassword);
        return mDao.insertMember(m);
    }

    @Override
    public Member selectOneMember(String id) {
        return mDao.selectOneMember(id);
    }

    @Override
    public int deleteMember(String userId) {
        Member m = new Member();
        m.setUserId(userId);
        return mDao.deleteMember(m);
    }

    @Override
    public int updateMember(Member m) {
        //멤버 m객체를 가져오고
        if (m.getUserPw() != null) {
            //비번 암호화
            String encodedPassword = passwordEncoder.encode(m.getUserPw());
            //그 m객체에서 비밀번호 부분을 encodedPassword로 셋해줌
            //그럼 이 m객체에서는 userId도 있고 encoded된 userPw도 있음
            // 그럼 이 m객체를 넘겨서 업데이트를못시키나..?
            m.setUserPw(encodedPassword);
            return mDao.updateMember(m.getUserId(),encodedPassword);
        }
        return 0;
    }
    @Override
    public Member selectByName(String keyword) {
        return mDao.selectByName(keyword);
    }

    @Override
    public Member selectByEmail(String email) {
        return mDao.selectByEmail(email);
    }

    @Override
    public Member findIdByEmail(String email) {
        return mDao.findIdByEmail(email);
    }


}
