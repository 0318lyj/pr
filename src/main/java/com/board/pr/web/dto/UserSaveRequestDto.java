package com.board.pr.web.dto;

import com.board.pr.domain.user.Role;
import com.board.pr.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    
    private String email;
    private String password;
    private Role role;

    @Builder
    public UserSaveRequestDto(String email, String password, Role role){
        this.email = email;
        this.password = password;
        this.role = role;
    }

    /**
     * 서비스에서 암호화된 비밀번호를 전달받아 엔티티로 변환
     */
    public User toEntity(String encodedPassword){
        return User.builder()
                .email(email)
                .password(encodedPassword)
                .role(role)
                .build();
    }
}
