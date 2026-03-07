package com.ecommerce.shopplus.config;

import com.ecommerce.shopplus.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/login", "/register", "/products/**", "/product/**",
                        "/css/**", "/js/**", "/images/**", "/fonts/**", "/font-face/**",
                        "/plugins/**", "/error").permitAll()
                .antMatchers("/account/**").authenticated()
                .anyRequest().permitAll()
            .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/products", true)
                .failureUrl("/login?error=true")
                .permitAll()
            .and()
            .logout()
                .logoutSuccessUrl("/login?logout=true")
                .permitAll()
            .and()
            .csrf().disable();

        return http.build();
    }
}
