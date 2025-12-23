package com.board.pr.domain.user;

import com.board.pr.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users") 
public class User extends BaseTimeEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email; //아이디 역할을 할 이메일

    @Column(nullable = false)
    private String password; //암호화된 비밀번호

    @Enumerated(EnumType.STRING) //Enum 이름을 문자열로 DB에 저장(USER, ADMMIN)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String email, String password, Role role){
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
