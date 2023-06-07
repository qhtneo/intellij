package com.project.trip.board.api.dto;

import com.project.trip.board.vo.Board;
import lombok.Builder;

public class BoardCommandDto {

    public class BoardWriteRequestDto{
        private int userNo;
        private String userId;
        private String userNickname;

        public Board toEntity(){
            Board board = Board.builder()
                    .userId(userId)
                    .userNo(userNo)
                    .userNickname(userNickname)
                    .build();
            return board;
    }
        @Builder
        public BoardWriteRequestDto(int userNo,String userId, String userNickname) {
            this.userNo = userNo;
            this.userId = userId;
            this.userNickname = userNickname;
        }
}
    public class BoardWriteResponseDto{

    }
}