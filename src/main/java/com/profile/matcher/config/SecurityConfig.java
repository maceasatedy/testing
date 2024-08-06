package com.profile.matcher.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/h2-console/**").permitAll() // Allow access to the H2 console
                        .requestMatchers("/profile/**").permitAll() // Allow access to the specific endpoint
                        .requestMatchers("/profile/{id}").permitAll()
                        .requestMatchers("/profile/**/**").permitAll()
                        .anyRequest().authenticated() // All other requests need to be authenticated
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**","/profile/**","/profile/{id}", "/profile/get_client_config/**")) // Disable CSRF protection for these endpoints
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin) // Allow frames from the same origin
                );

        return http.build();
    }
}
