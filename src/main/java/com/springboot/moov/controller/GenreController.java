package com.springboot.moov.controller;

import com.springboot.moov.data.dto.GenreDto;
import com.springboot.moov.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GenreController {

    @Autowired
    private GenreService genreService;

    // HTML 페이지를 반환
    @GetMapping("/rec_genre_1")
    public String getGenrePage() {
        return "rec_genre_1"; // `src/main/resources/templates/rec_genre_1.html`로 렌더링
    }

    // JSON 데이터 반환
    @GetMapping("/genres/api/{genre}")
    @ResponseBody
    public List<GenreDto> getMoviesByGenre(@PathVariable String genre) {
        return genreService.getMoviesByGenre(genre);
    }
}
