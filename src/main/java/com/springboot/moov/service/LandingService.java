package com.springboot.moov.service;

import com.springboot.moov.entity.Landing;
import com.springboot.moov.repository.LandingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LandingService {

    private final LandingRepository landingRepository;

    @Autowired
    public LandingService(LandingRepository landingRepository) {
        this.landingRepository = landingRepository;
    }

    @Transactional(readOnly = true)
    public List<Landing> getAllLandings() {
        return landingRepository.findAll(); // 모든 랜딩 데이터 가져오기
    }

    @Transactional(readOnly = true)
    public List<Landing> getSortedAndDistinctByReleaseDate() {
        LocalDate currentDate = LocalDate.now();

        // 모든 랜딩 데이터 가져오기
        List<Landing> allLandings = landingRepository.findAll();

        // release_date가 현재 날짜 이전인 데이터를 필터링하고 최신순으로 정렬
        List<Landing> sortedByReleaseDate = allLandings.stream()
                .filter(landing -> landing.getReleaseDate().isBefore(currentDate))
                .sorted(Comparator.comparing(Landing::getReleaseDate).reversed())
                .collect(Collectors.toList());

        // 중복된 영화 제목을 제거하고 최신 데이터만 유지
        List<Landing> distinctSortedByReleaseDate = sortedByReleaseDate.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(Landing::getTitle, landing -> landing, (existing, replacement) -> existing),
                        map -> map.values().stream()
                                .sorted(Comparator.comparing(Landing::getReleaseDate).reversed())
                                .collect(Collectors.toList())
                ));

        // 상위 10개 데이터만 추출
        return distinctSortedByReleaseDate.stream().limit(10).collect(Collectors.toList());
    }
}