package com.springboot.moov.data.repository;

import com.springboot.moov.data.entity.Landing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LandingRepository extends JpaRepository<Landing, Long> {

}
