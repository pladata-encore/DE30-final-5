package com.springboot.moov.controller;

import com.springboot.moov.entity.Landing;
import com.springboot.moov.service.LandingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LandingController {

    @Autowired
    private LandingService landingService;

    @GetMapping("/")
    public String landing(Model model) {
        List<Landing> landings = landingService.getAllLandings(); // 모든 랜딩 데이터 가져오기
        List<Landing> sortedAndDistinctByReleaseDate = landingService.getSortedAndDistinctByReleaseDate(); // 최신 날짜 기준으로 정렬된 데이터 가져오기

        // 상위 10개 데이터만 추출 후 역순으로 정렬
        List<Landing> top10SortedAndDistinctByReleaseDate = sortedAndDistinctByReleaseDate.stream()
                .limit(10)
                .collect(Collectors.toList());
        Collections.reverse(top10SortedAndDistinctByReleaseDate); // 역순 정렬

        model.addAttribute("landings", landings); // 첫 번째 슬라이드에 표시할 데이터
        model.addAttribute("sortedAndDistinctByReleaseDate", top10SortedAndDistinctByReleaseDate); // 두 번째 슬라이드에 표시할 데이터

        return "landing";
    }
}
