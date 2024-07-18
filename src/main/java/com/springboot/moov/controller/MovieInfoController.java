package com.springboot.moov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovieInfoController {

    // 영화 검색 페이지로 이동
    @GetMapping("/movies/search")
    public String searchMoviePage() {
        return "search_movies"; // search_movies.html 뷰 페이지를 반환
    }

    // 영화 검색 기능
    @GetMapping("/movies")
    public String searchMovies(@RequestParam("query") String query, Model model) {
        // 여기서는 간단히 예시를 위해 검색어(query)를 모델에 담아서 전달합니다.
        model.addAttribute("query", query);
        return "search_results"; // 검색 결과를 보여줄 페이지 (search_results.html)
    }

    // 영화 상세 정보 조회
    @GetMapping("/movies/{id}")
    public String movieDetail(@PathVariable("id") Long id, Model model) {
        // 여기서는 간단히 예시를 위해 영화 ID를 받아서 해당 영화의 상세 정보를 조회하여 모델에 담습니다.
        // 실제로는 데이터베이스 조회 등의 로직이 들어갑니다.
        String movieTitle = "영화 제목";
        String movieDirector = "감독 이름";
        // 예시로 모델에 영화 정보 추가
        model.addAttribute("title", movieTitle);
        model.addAttribute("director", movieDirector);
        return "movie_detail"; // 영화 상세 정보 페이지 (movie_detail.html)
    }
}
