package com.project.trip.reply.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
    private int replyId;            // 댓글 번호
    private int boardNo;            // 글 번호
    private int userNo;             // 작성자 번호
    private String userNickname;    // 작성자 닉네임
    private String replyContent;    // 댓글 내용
    private String replyInputDate;  // 댓글 작성일
}
