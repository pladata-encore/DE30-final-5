package com.springboot.moov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(new AntPathRequestMatcher("/**")).permitAll() // 로그인 페이지와 정적 자원 접근 허용
                        .anyRequest().authenticated() // 나머지 요청은 인증 필요
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/rec_intro.html") // 로그인 성공 시 리다이렉트할 페이지
                        .permitAll() // 로그인 페이지 접근 허용
                )
                .logout(logout -> logout
                        .permitAll() // 로그아웃 페이지 접근 허용
                )
                .csrf(csrf -> csrf.disable()); // CSRF 보호 비활성화
        return http.build();
    }
}
