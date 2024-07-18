package com.springboot.moov;

import com.opencsv.CSVReader;
import com.springboot.moov.entity.Landing;
import com.springboot.moov.repository.LandingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private final LandingRepository movieRepository;

    @Autowired
    public DataLoader(LandingRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ClassPathResource resource = new ClassPathResource("all_movies_data.csv");
        try (InputStream is = resource.getInputStream();
             InputStreamReader reader = new InputStreamReader(is);
             CSVReader csvReader = new CSVReader(reader)) {

            List<Landing> movies = new ArrayList<>();
            String[] headers = csvReader.readNext(); // 헤더를 읽어 건너뜀

            String[] row;
            int lineNumber = 1;
            while ((row = csvReader.readNext()) != null) {
                lineNumber++;
                String title = row[0].trim(); // 제목
                String posterUrl = row[6].trim(); // 포스터 URL
                String releaseDateString = row[5].trim(); // 개봉일

                // 개봉일이 "(NULL)"이면 건너뛰기
                if (releaseDateString.equals("(NULL)")) {
                    System.out.println("Line " + lineNumber + ": Release date is (NULL), skipping...");
                    continue;
                }

                // 개봉일 데이터 파싱
                LocalDate releaseDate = parseReleaseDate(releaseDateString);
                if (releaseDate == null) {
                    System.out.println("Line " + lineNumber + ": Failed to parse release date: " + releaseDateString);
                    continue;
                }

                // Landing 객체 생성 및 리스트에 추가
                Landing movie = new Landing();
                movie.setTitle(title);
                movie.setPosterUrl(posterUrl);
                movie.setReleaseDate(releaseDate);

                movies.add(movie);
            }

            // 영화 데이터 저장
            movieRepository.saveAll(movies);

        } catch (IOException e) {
            e.printStackTrace(); // 예외 처리 로직 추가 필요
        }
    }

    private LocalDate parseReleaseDate(String releaseDateString) {
        if (releaseDateString.isEmpty() || releaseDateString.equals("(NULL)")) {
            return null; // 빈 문자열이나 (NULL)인 경우 null 반환
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(releaseDateString, formatter);
        } catch (Exception e) {
            // 날짜 형식 오류 처리
            System.err.println("Failed to parse release date: " + releaseDateString);
            e.printStackTrace();
            return null; // 날짜 파싱 오류인 경우 null 반환
        }
    }
}

