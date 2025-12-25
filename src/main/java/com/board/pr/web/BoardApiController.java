package com.board.pr.web;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.board.pr.service.BoardService;
import com.board.pr.web.dto.BoardListResponseDto;
import com.board.pr.web.dto.BoardResponseDto;
import com.board.pr.web.dto.BoardSaveRequestDto;
import com.board.pr.web.dto.BoardUpdateRequestDto;

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
    public Long save(@RequestBody BoardSaveRequestDto requestDto, Principal principal){
        /**
         * Principal 객체: 현재 인증된 사용자의 정보를 담고 있다.
         * principal.getName(): 설정에서는 사용자의 '이메일'을 반환한다.
         * 클라이언트가 주는 데이터가 아닌, 서버가 인증한 세션 정보를 사용하므로 보안상 안전하다.
         */
        return boardService.save(requestDto, principal.getName());
    }

    /**
     * GET 방식으로 /api/v1/posts/list 요청이 오면 전체 게시글 목록을 반환
     */
    @GetMapping("/api/v1/posts/list")
    public List<BoardListResponseDto> findAll(){
        //서비스에서 최신순으로 정렬된 DTO 리스트를 받아와서 클라이어트에게 던진다.
        return boardService.findAllDesc();
    }

    /**
     * 상세조회
     */
    @GetMapping("api/v1/posts//{id}")
    public BoardResponseDto findById(@PathVariable("id") Long id){
        return boardService.findById(id);
    }

    /**
     * 수정
     */
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable("id") Long id, @RequestBody BoardUpdateRequestDto requestDto, Principal principal){
        return boardService.update(id, requestDto, principal.getName());
    }


    /**
     * 삭제
     */
    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable("id") Long id,Principal principal){
        boardService.delete(id, principal.getName());
    return id;
    }
}
