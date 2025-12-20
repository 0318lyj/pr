package com.board.pr.domain.board;

import com.board.pr.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //무분별한 객체 생성방지
public class Board extends BaseTimeEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    @Builder
    public Board(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    //Update 메서드 (Setter 대신 비즈니스 메서드 사용)
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
