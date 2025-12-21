package com.board.pr.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.board.pr.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller //HTML 뷰를 반환할때는 @RestController를 쓰면 안됨
public class IndexController {
    
    private final BoardService boardService;

    /**
     * 메인페이지 (게시글 목록 화면)
     */
    @GetMapping("/")
    public String index(Model model){
        //1. 서비스에서 게시글 목록을 가져온다
        //2. 모델(Model)이라는 바구니에 "posts"라는 이름으로 목록을 담는다.
        //3. 타임리프가 이 바구니를 들고 index.html로 가서 데이터를 채운다.
        model.addAttribute("posts", boardService.findAllDesc());

        return "index";
    }
    
    /**
     * 글 등록 페이지 이동
     */
    @GetMapping("/posts/save")
    public String postSave(){
        return "posts-save";
    }
}
