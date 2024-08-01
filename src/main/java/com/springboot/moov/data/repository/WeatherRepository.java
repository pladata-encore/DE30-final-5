package com.springboot.moov.data.repository;

import com.springboot.moov.data.entity.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {
    // 추가적인 쿼리 메소드가 필요한 경우 여기에 정의
}
