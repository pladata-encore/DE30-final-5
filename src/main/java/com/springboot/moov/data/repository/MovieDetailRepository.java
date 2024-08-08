package com.springboot.moov.data.repository;

import com.springboot.moov.data.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieDetailRepository extends JpaRepository<Movies, Long> {
    // 필요한 경우 커스텀 쿼리 메소드 추가
}
