-- 테이블이 이미 존재하지 않는 경우에만 생성
CREATE TABLE IF NOT EXISTS movies_feelings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    genre VARCHAR(100),
    ratings DOUBLE
);
