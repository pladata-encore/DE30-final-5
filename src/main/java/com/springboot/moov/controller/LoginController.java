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
    @PostMapping("/perform_login")
    public String performLogin(String email_address, String password) {
        if ("user@user.com".equals(email_address) && "password".equals(password)) {
            return "redirect:/rec_intro"; // 로그인 성공 시 리다이렉트할 페이지
        }
        return "redirect:/login"; // 로그인 실패 시 리다이렉트할 페이지
    }

//    @GetMapping("/rec_intro")
//    public String recIntro() {
//        return "rec_intro"; // src/main/resources/templates/rec_intro.html 템플릿을 반환합니다.
//    }

    private boolean authenticateUser(String username, String password) {
        // 예시: 간단한 사용자 인증 로직 (실제로는 보안에 취약하므로 사용하지 말고 실제 인증 시스템을 사용하세요)
        return "user".equals(username) && "pass".equals(password);
    }
}
