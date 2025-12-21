package com.board.pr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.pr.domain.board.BoardRepository;
import com.board.pr.web.dto.BoardSaveRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 선언된 필드(Repository)에 대해 생성자를 자동 생성하여 의존성 주입
@Service
public class BoardService {
    
    // @RequiredArgsConstructor 때문에 @Autowired 안해도 됨
    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardSaveRequestDto requestDto){
        // 리포지토리를 통해 엔티티를 DB에 저장하고, 생성된 ID 반환
        return boardRepository.save(requestDto.toEntity()).getId();
    }
}
