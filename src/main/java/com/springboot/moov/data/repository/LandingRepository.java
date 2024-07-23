//package com.springboot.moov.data.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Repository
//public interface LandingRepository extends JpaRepository<Landing, Long> {
//
//    @Query("SELECT l FROM Landing l WHERE l.releaseDate < :currentDate GROUP BY l.title ORDER BY l.releaseDate DESC")
//    List<Landing> findSortedAndDistinctByReleaseDate(LocalDate currentDate);
//}
