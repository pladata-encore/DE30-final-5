package com.springboot.moov.repository;

import com.springboot.moov.entity.Landing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LandingRepository extends JpaRepository<Landing, Long> {

}
