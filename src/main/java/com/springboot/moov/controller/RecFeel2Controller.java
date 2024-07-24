package com.springboot.moov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecFeel2Controller {

    @GetMapping("/rec_feel_2")
    public String getRecFeel2Page() {
        return "rec_feel_2";
    }
}
