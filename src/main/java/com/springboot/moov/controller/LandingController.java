package com.springboot.moov.controller;

import com.springboot.moov.data.dto.LandingDTO;
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
        List<LandingDTO> landings = landingService.getAllLandings();
        List<LandingDTO> sortedAndDistinctByReleaseDate = landingService.getSortedAndDistinctByReleaseDate();

        // 상위 10개 데이터만 추출 후 역순으로 정렬
        List<LandingDTO> top10SortedAndDistinctByReleaseDate = sortedAndDistinctByReleaseDate.stream()
                .limit(10)
                .collect(Collectors.toList());
        Collections.reverse(top10SortedAndDistinctByReleaseDate); // 역순 정렬

        model.addAttribute("landings", landings);
        model.addAttribute("sortedAndDistinctByReleaseDate", top10SortedAndDistinctByReleaseDate);

        return "landing";
    }
}
