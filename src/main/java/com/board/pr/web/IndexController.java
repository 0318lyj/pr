package com.board.pr.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.pr.service.BoardService;
import com.board.pr.web.dto.BoardResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller //HTML 뷰를 반환할때는 @RestController를 쓰면 안됨
public class IndexController {
    
    private final BoardService boardService;

    /**
     * 메인페이지 (게시글 목록 화면)
     */
    @GetMapping("/")
    public String index(Model model, @RequestParam(value = "keyword", required = false) String keyword){
        if (keyword != null){
            model.addAttribute("posts", boardService.search(keyword));
        } else {
            model.addAttribute("posts", boardService.findAllDesc());
        }
        return "index";
    }
    
    /**
     * 글 등록 페이지 이동
     */
    @GetMapping("/posts/save")
    public String postSave(){
        return "posts-save";
    }

    /**
     * 글 상세조회
     */
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable("id") Long id, Model model){
        BoardResponseDto dto = boardService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }

    /**
     * 회원가입 페이지 이동
     */
    @GetMapping("/user/signup")
    public String useSignup(){
        // templates/user-signup.html 파일을 찾아서 반환
        return "user-signup";
    }

    /**
     * 로그인 페이지 이동
     */
    @GetMapping("/login")
    public String login() {
        return "login"; // templates/login.html을 반환
    }

}
