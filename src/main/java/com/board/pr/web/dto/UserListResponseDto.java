package com.board.pr.web.dto;

import java.time.LocalDateTime;

import com.board.pr.domain.user.User;

import lombok.Getter;

@Getter
public class UserListResponseDto {
    private Long id;
    private String email;
    private String role;
    private LocalDateTime createdDate;

    public UserListResponseDto(User entity){
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.role = entity.getRole().name();
        this.createdDate =entity.getCreatedDate();
    }
}
