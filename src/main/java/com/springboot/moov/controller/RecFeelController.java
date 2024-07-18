package com.springboot.moov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecFeelController {

    @GetMapping("/rec-feel-1")
    public String recFeel1Page() {
        return "rec_feel_1"; // rec_feel_1.html 뷰 페이지를 반환
    }
}
