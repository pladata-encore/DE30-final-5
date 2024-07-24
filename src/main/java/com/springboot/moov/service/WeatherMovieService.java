package com.springboot.moov.service;

import com.springboot.moov.data.dto.WeatherMovieDTO;
import com.springboot.moov.data.repository.WeatherMovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherMovieService {

    private final WeatherMovieRepository weatherMovieRepository;

    public WeatherMovieService(WeatherMovieRepository weatherMovieRepository) {
        this.weatherMovieRepository = weatherMovieRepository;
    }

    public List<WeatherMovieDTO> getRecommendations(String weather) {
        String genre;
        switch (weather.toLowerCase()) {
            case "thunderstorm":
                genre = "SF, 스릴러";
                break;
            case "drizzle":
                genre = "로맨스, 음악, 드라마";
                break;
            case "rain":
                genre = "로맨스, 범죄";
                break;
            case "snow":
                genre = "가족, 애니메이션";
                break;
            case "clear":
                genre = "모험, 코미디, 드라마";
                break;
            case "clouds":
                genre = "공포, 미스터리";
                break;
            case "windy":
                genre = "역사, 서부";
                break;
            case "mist":
                genre = "다큐멘터리, 모험, 판타지";
                break;
            case "dust":
                genre = "서부, 액션";
                break;
            case "typhoon":
                genre = "전쟁, 액션";
                break;
            default:
                genre = "";
                break;
        }
        return weatherMovieRepository.findMoviesByGenre(genre);
    }
}

