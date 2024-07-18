package com.springboot.moov;


import com.springboot.moov.entity.User;

import java.util.List;

public interface UserDao {

    // 사용자 생성
    void createUser(User user);

    // 사용자 조회 (이메일 기준)
    User getUserByEmail(String email);

    // 사용자 조회 (닉네임 기준)
    User getUserByNickname(String nickname);

    // 사용자 수정
    void updateUser(User user);

    // 사용자 삭제 (이메일 기준)
    void deleteUserByEmail(String email);

    // 모든 사용자 조회
    List<User> getAllUsers();

//    // 사용자의 좋아요한 영화 목록 조회
//    List<MovieInfo> getLikedMovies(String userEmail);
//
//    // 사용자의 위시리스트 조회
//    List<MovieInfo> getWishlist(String userEmail);
//
//    // 사용자의 영화 추천 목록 조회
//    List<MovieInfo> getRecommendedMovies(String userEmail);
//
//    // 사용자의 영화 리뷰 조회
//    List<MovieReview> getMovieReviews(String userEmail);
//
//    // 특정 사용자의 특정 영화에 대한 리뷰 작성
//    void writeMovieReview(String userEmail, MovieReview review);
//
//    // 사용자의 특정 영화에 대한 리뷰 수정
//    void updateMovieReview(String userEmail, MovieReview review);

    // 사용자의 특정 영화에 대한 리뷰 삭제
    void deleteMovieReview(String userEmail, int reviewId);
}
