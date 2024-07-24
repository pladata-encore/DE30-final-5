package com.springboot.moov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecIntroController {

    @GetMapping("/rec_intro")
    public String showRecIntro() {
        return "rec_intro"; // src/main/resources/templates/rec_intro.html
    }

    @GetMapping("/rec_feel_1")
    public String showRecFeel1() {
        return "rec_feel_1"; // src/main/resources/templates/rec_feel_1.html
    }
}