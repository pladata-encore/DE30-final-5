package com.springboot.moov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;


@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(@RequestParam(name = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", true);
        }
        return "login";
    }

    @PostMapping("/login")
    public String performLogin(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) {
        // 예시: 간단한 인증 로직 구현
        boolean authenticated = authenticateUser(username, password);

        if (authenticated) {
            // 인증 성공 시 홈 페이지로 리다이렉트
            return "redirect:/home";
        } else {
            // 인증 실패 시 에러 파라미터와 함께 로그인 페이지로 리다이렉트
            redirectAttributes.addAttribute("error", true);
            return "redirect:/login";
        }
    }

    private boolean authenticateUser(String username, String password) {
        // 예시: 간단한 사용자 인증 로직 (실제로는 보안에 취약하므로 사용하지 말고 실제 인증 시스템을 사용하세요)
        return "user".equals(username) && "pass".equals(password);
    }
}
