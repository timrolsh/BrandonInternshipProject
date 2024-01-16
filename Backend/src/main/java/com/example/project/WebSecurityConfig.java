package com.example.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.and())
                .csrf(csrf -> csrf.disable())
                // .headers(headers -> headers.frameOptions().disable());
                .headers()
                .xssProtection()
                .disable();

        http
                .authorizeHttpRequests((requests) -> requests

                        // .requestMatchers("/login", "/register").permitAll()
                        // .anyRequest().authenticated()
                        // .requestMatchers("/**").permitAll()
                        .anyRequest().permitAll());
        http
                .formLogin((form) -> form
                        // .loginPage("/login")
                        .permitAll(true));
        http
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

}
