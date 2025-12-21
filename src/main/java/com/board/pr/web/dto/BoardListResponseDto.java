package com.board.pr.web.dto;

import java.time.LocalDateTime;

import com.board.pr.domain.board.Board;

import lombok.Getter;

@Getter
public class BoardListResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;

    /**
     * 엔티티의 일부 필드만 뽑아서 DTO로 변환하는 생성자
     * 목록 화면에선는 굳이 무거운 '내용(content)'까지 다 가져올 필요 없음
     */
    public BoardListResponseDto(Board entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifieDate();
    }
}
