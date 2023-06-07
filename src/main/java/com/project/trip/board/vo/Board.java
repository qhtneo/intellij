package com.project.trip.board.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
    private int boardNo;        // 글 번호
    private int userNo;         // 작성자 번호
    private String userId;
    private String userNickname;   // 작성자 닉네임
    private String boardTitle;     // 글제목
    private String boardSummary;   // 글내용
    private String inputDate;      // 글 작성 날짜
    private int boardView;         // 글 조회수
    private int recommend;         // 글 좋아요
    private String localCategory;  // 글 지역별 검색
}
