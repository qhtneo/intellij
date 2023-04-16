package com.project.trip.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    private int boardNo;        // 글 번호
    private int userNo;         // 작성자 번호
    private String userNickname;   // 작성자 닉네임
    private String boardTitle;     // 글제목
    private String boardSummary;   // 글내용
    private String inputDate;      // 글 작성 날짜
    private int boardView;         // 글 조회수
    private int boardLike;         // 글 좋아요
}
