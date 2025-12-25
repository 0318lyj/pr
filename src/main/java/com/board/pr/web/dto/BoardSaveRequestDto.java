package com.board.pr.web.dto;

import com.board.pr.domain.board.Board;
import com.board.pr.domain.user.User;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardSaveRequestDto {
    
    private String title;
    private String content;
    private String author;

    /**
     * DTO에서 엔티티를 생성하는 메서드
     * DB 구조를 가진 엔티티 보호하기 위해, 요청 데이터를 이 메서드를 통해 엔티티로 변환
     */
    public Board toEntity(User user){
        return Board.builder()
                .title(title)
                .content(content)
                .author(author)
                .user(user)
                .build();
    }
}
