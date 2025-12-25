package com.board.pr.web;

import com.board.pr.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final UserService userService;

    AdminController(UserService userService) {
        this.userService = userService;
    }
    
    /**
     * @preAuthorize(): 메서드 실행 전 권한을 체크
     * SecurityConfig에서도 설정했는데, 메서드 단위에서 한 번 더 걸어주면 이중 방어가 된다.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/dashboard")
    public String adminDashboard(){
        return "admin-dashboard"; //관리자 전용 대시보드 화면
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/users")
    public String userList(Model model){
        model.addAttribute("users", userService.findAll());
        return "admin-users"; 
    }
}
