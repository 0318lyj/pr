package com.board.pr.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * 소셜 로그인이나 일반 로그인 시 이미 가입된 사용자인지 확인하기 위해
     * 이메일로 사용자를 찾는 기능을 추가한다.
     */
    Optional<User> findByEmail(String email);
}
