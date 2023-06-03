package com.example.knjiznica.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.knjiznica.service.KorisnikService;

@Configuration
public class SecurityConfig{

    
    private KorisnikService korisnikService;
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
        		.requestMatchers("/", "/korisnik/register").permitAll()
                .anyRequest().permitAll()
                .and()
            .formLogin()
                .loginPage("/korisnik/login")
                .defaultSuccessUrl("/home",true)
                .permitAll()
                .and()
            .logout()
                .permitAll();
        return http.build();
    }

  
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
}

    




    



