package com.springboot.moov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherController {

    @GetMapping("/rec_weather_1")
    public String recWeather1(Model model) {
        return "rec_weather_1"; // templates 폴더의 HTML 파일 이름
    }

    @GetMapping("/rec_weather_2")
    public String recWeather2(Model model) {
        return "rec_weather_2"; // templates 폴더의 HTML 파일 이름
    }

    @GetMapping("/rec_weather_3")
    public String recWeather3(Model model) {
        return "rec_weather_3"; // templates 폴더의 HTML 파일 이름
    }

    @GetMapping("/rec_weather_3_1")
    public String recWeather3_1(Model model) {
        return "rec_weather_3_1"; // templates 폴더의 HTML 파일 이름
    }
}
