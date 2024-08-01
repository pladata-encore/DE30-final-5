package com.springboot.moov.service;

<<<<<<< HEAD
import com.springboot.moov.data.response.WeatherResponse;

public interface WeatherService {
    WeatherResponse getCurrentWeather(double lat, double lon);
=======
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getWeatherGenre(String location) {
        String url = String.format("%s?q=%s&appid=%s", apiUrl, location, apiKey);
        String response = restTemplate.getForObject(url, String.class);
        JSONObject jsonResponse = new JSONObject(response);

        // 날씨 데이터에서 필요한 정보를 추출
        String weatherCondition = jsonResponse.getJSONArray("weather").getJSONObject(0).getString("main");

        // 날씨와 장르 매핑 예시
        return mapWeatherToGenre(weatherCondition);
    }

    private String mapWeatherToGenre(String weatherCondition) {
        switch (weatherCondition) {
            case "Thunderstorm":
                return "SF, Thriller";
            case "Drizzle":
                return "Romance, Music, Drama";
            case "Rain":
                return "Romance, Crime";
            case "Snow":
                return "Family, Animation, Fantasy";
            case "Clear":
                return "Adventure, Comedy, Drama";
            case "Clouds":
                return "Horror, Mystery, Drama";
            case "Windy":
                return "History, Western";
            case "Mist":
            case "Fog":
                return "Documentary, Adventure, Fantasy";
            case "Dust":
                return "Western, Action";
            case "Typhoon":
                return "War, Action";
            default:
                return "TV Movie, Drama, SF";
        }
    }

    public String getWeather(String location) {
        String url = String.format("%s?q=%s&appid=%s", apiUrl, location, apiKey);
        String response = restTemplate.getForObject(url, String.class);
        JSONObject jsonResponse = new JSONObject(response);

        // 날씨 데이터에서 필요한 정보를 추출
        return jsonResponse.getJSONArray("weather").getJSONObject(0).getString("main");
    }
>>>>>>> origin/master
}
