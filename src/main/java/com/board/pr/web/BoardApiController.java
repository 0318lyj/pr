package com.board.pr.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.board.pr.service.BoardService;
import com.board.pr.web.dto.BoardSaveRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController //Json 형태로 데이터를 응답하는 컨트롤러임을 명시
public class BoardApiController {
    
    private final BoardService boardService;

    /**
     * Post 방식으로 /api/v1/posts 요청이 오면 게시글 저장
     * @RequesetBody: 클라이언트가 보낸 Json 데이터를 DTO 객체로 변환해줍니다.
     */
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody BoardSaveRequestDto requestDto){
        //컨트롤러는 요청을 받아 서비스에 처리를 넘기고, 그 걸과를 다시 반환
        return boardService.save(requestDto);
    }
}
