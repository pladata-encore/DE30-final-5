package com.springboot.moov.controller;

import com.springboot.moov.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/rec_weather_1")
    public String recWeather1(Model model) {
        return "rec_weather_1"; // templates 폴더의 HTML 파일 이름
    }
}
