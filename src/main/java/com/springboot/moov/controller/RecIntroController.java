package com.springboot.moov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecIntroController {


    @GetMapping("/rec-intro")
    public String showRecIntroPage() {
        return "rec_intro"; // templates/rec_intro.html 파일명과 일치해야 합니다.
    }
}