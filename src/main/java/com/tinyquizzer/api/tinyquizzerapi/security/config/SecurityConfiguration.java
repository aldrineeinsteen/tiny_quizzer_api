package com.tinyquizzer.api.tinyquizzerapi.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeHttpRequests((requests) -> requests
                                //.requestMatchers("/api/v1/**").authenticated()
                                .requestMatchers(HttpMethod.POST, "/api/v1/categories").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/questions/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/questions/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/v1/questions").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/questions/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/questions/**").hasRole("ADMIN")
                                .requestMatchers("/api/v1/**").authenticated()
                        //.anyRequest().permitAll()
                )
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
        return http.build();
    }

    @Bean
    @Profile("dev")
    public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password(bCryptPasswordEncoder.encode("userPass"))
                .roles("USER")
                .build());
        manager.createUser(User.withUsername("admin")
                .password(bCryptPasswordEncoder.encode("adminPass"))
                .roles("USER", "ADMIN")
                .build());
        return manager;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
}

