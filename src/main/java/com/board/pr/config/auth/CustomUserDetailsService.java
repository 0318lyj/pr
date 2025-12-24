package com.board.pr.config.auth;



import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.board.pr.domain.user.User;
import com.board.pr.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService{
    
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 1. DB에서 이메일로 사용자를 찾는다.
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("해당사용자가 존재하지 않습니다. :" + email));
        
        /**
         * 2. 찾은 사용자 정보를 스프링 시큐리티가 이해할 수 있는 형태(UserDetails)로 변환
         * 여기서 사용자의 이메일, 암호화된 비밀번호, 권한(Role)을 넘겨준다.
         */
        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getPassword(),
            Collections.singleton(new SimpleGrantedAuthority(user.getRole().getKey()))
        );
    }
}
