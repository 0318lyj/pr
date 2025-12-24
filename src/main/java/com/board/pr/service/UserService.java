package com.board.pr.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.pr.domain.user.UserRepository;
import com.board.pr.web.dto.UserSaveRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public Long save(UserSaveRequestDto requestDto){
        /**
         * 보안팁: 비밀번호는 절대 평문으로 저장하면 안된다.
         * encoder.encode()를 통해 복호화가 불가능한 해시값으로 변환하여 저장한다.
         */
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        return userRepository.save(requestDto.toEntity(encodedPassword)).getId();
    }
}
