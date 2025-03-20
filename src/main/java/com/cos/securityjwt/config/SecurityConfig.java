package com.cos.securityjwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 비밀번호를 해쉬로 암호화시켜서 검증하고 진행하게 됨
    // 이걸 통해서 암호화를 진행하는 데 사용
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // csrf disable
        // 세션 방식에서는 세션이 고정되기에 csrf 공격이 필수적으로 방어를 해줘야하는데
        // jwt 방식은 세션을 stateless 방식으로 방어하기에 필요없으니 disable 상태로 둘 것임
        http
                .csrf((auth) -> auth.disable());

        // jwt 방식으로 로그인 진행할 것이
        //From 로그인 방식 disable
        http
                .formLogin((auth) -> auth.disable());

        //http basic 인증 방식 disable
        http
                .httpBasic((auth) -> auth.disable());


//        // 윗 부분 더 간단하게 할 수 있음
//        http.httpBasic(HttpBasicConfigurer::disable)
//                .csrf(CsrfConfigurer::disable)
//                .cors(CorsConfigurer::disable)
//                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        // 인가작업
        //경로별 인가 작업
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/login", "/", "/join").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN") // admin 권한을 가진 사용자만 접근 가능
                        .anyRequest().authenticated()); // 로그인한 사용자만 접근할 수 있음

        // 인가 작업은 http 인자의 authorizeHttpRequests 메소드에서 진행

        // 제일 중요한 것은 JWT 방식은 세션을 stateless 상태로 관리하게 된다
        // 세션 설정
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // 세션을 disable 하는 것과 stateless로 설정하는 것의 차이 :
        // disable 설정 같은 경우 세션 설정과 관련된 모든 설정을 null 객체로 처리하기 때문에 세션 관련 모든 설정자체가 지원되지 않는다.
        // stateless 설정 같은 경우 세션 저장 부분에 대해서만 적용하기 때문에 나머지 설정이 가능하다.


        return http.build();
    }
}
