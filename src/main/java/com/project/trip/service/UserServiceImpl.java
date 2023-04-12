package com.project.trip.service;

import com.project.trip.dao.UserDao;
import com.project.trip.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao uDao;
    @Override
    public int joinUser(User u) {
        return uDao.insertUser(u);
    }
}
