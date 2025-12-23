package com.board.pr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //스프링 시큐리티 설정을 활성화합니다.
public class SecurityConfig {
    
    //비밀번호를 안전하게 암호화해주는 빈. 해커는 원문 비번을 알 수 없어야 한다.
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain fileterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) //실습 편의를 위해 CSRF 방어 잠시 해제(실제 서비스에서는 필수다.)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/api/v1/posts/list", "/js/**", "/css/**").permitAll() //누구나 접근가능
                .requestMatchers("/api/v1/posts/**").hasAnyRole("USER", "ADMIN") //회원/관리자만 글쓰기 가능
                .requestMatchers("/admin/**").hasRole("ADMIN") //관리자만 들어가는 페이지
                .anyRequest().authenticated() //그 외 모든 요청은 로그인이 필요함
            )
            .formLogin(login -> login
                .loginPage("/login") //로그인 페이지 주소
                .defaultSuccessUrl("/") //성공 시 이동할 주소
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/") //로그아웃 성공 시 이동
            );

            return http.build();
    }
}
