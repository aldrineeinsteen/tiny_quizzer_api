package com.tinyquizzer.api.tinyquizzerapi.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

//    @Autowired
//    UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeHttpRequests((requests) -> requests
                                //.requestMatchers("/api/v1/**").authenticated()
                                //.requestMatchers(HttpMethod.POST, "/api/v1/categories").hasRole("ROLE_ADMIN")
                                //.requestMatchers(HttpMethod.DELETE, "/api/v1/questions/**").hasRole("ROLE_ADMIN")
                                //.requestMatchers(HttpMethod.PUT, "/api/v1/questions/**").hasRole("ROLE_ADMIN")
                                //.requestMatchers(HttpMethod.POST, "/api/v1/questions").hasRole("ROLE_ADMIN")
                                //.requestMatchers(HttpMethod.DELETE, "/api/v1/questions/**").hasRole("ROLE_ADMIN")
                                //.requestMatchers(HttpMethod.PUT, "/api/v1/questions/**").hasRole("ROLE_ADMIN")
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

/*    @Bean
    @Profile("dev")
    public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password(bCryptPasswordEncoder.encode("userPass"))
                .roles("ROLE_USER")
                .build());
        manager.createUser(User.withUsername("admin")
                .password(bCryptPasswordEncoder.encode("adminPass"))
                .roles("ROLE_USER", "ROLE_ADMIN")
                .build());
        return manager;
    }*/

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }
}

