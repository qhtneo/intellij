package com.project.trip.dao;

import org.apache.ibatis.annotations.Mapper;
import com.project.trip.vo.User;

@Mapper
public interface UserDao {
    public int insertUser(User U);
}
