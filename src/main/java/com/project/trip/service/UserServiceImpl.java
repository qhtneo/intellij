package com.project.trip.service;

import com.project.trip.dao.UserDao;
import com.project.trip.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao uDao;
    @Override
    public int joinUser(User u) {
        String encodedPassword = passwordEncoder.encode(u.getPassword());

        //비번 새로 설정
        u.setUserPw(encodedPassword);
        return uDao.insertUser(u);
    }
}
