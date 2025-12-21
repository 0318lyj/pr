package com.board.pr.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.pr.domain.board.BoardRepository;
import com.board.pr.web.dto.BoardListResponseDto;
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

    /**
     * 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도를 개선.
     * 등록, 수정, 삭제 기능이 전혀 없는 메서드에서 사용하면 성능상 이점이 있습니다.
     */
    @Transactional(readOnly = true)
    public List<BoardListResponseDto> findAllDesc() {
        return boardRepository.findAllDesc().stream()
                .map(BoardListResponseDto::new) //결과로 넘어온 Board 엔티티들을 DTO로 변환
                .collect(Collectors.toList()); //변환된 DTO들을 리스트로 묶어서 반환
    }
}
