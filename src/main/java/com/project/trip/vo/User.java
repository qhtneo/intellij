package com.project.trip.vo;

import lombok.Data;

@Data
public class User {
    private String userNo;
    private String userName;
    private String userPw;
    private String userNickname;
    private String userEmail;
    private String regDate;
    private boolean userEmailYn;
}
