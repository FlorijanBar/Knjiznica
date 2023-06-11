package com.example.knjiznica.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.knjiznica.service.KorisnikServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private KorisnikServiceImpl korisnikService;
	  @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http.csrf().disable()
	                .authorizeHttpRequests((requests) -> requests
	                        .requestMatchers("/korisnik/registracija/**").permitAll()
	                        .requestMatchers("/korisnik/login/**").permitAll()
	                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
	                        .anyRequest().permitAll()
	                )
	                .formLogin((form) -> form
	                        .loginPage("/korisnik/login")
	                        .loginProcessingUrl("/korisnik/login")
	                        .defaultSuccessUrl("/home/")
	                        .permitAll()
	                )
	                .logout((logout) -> logout.permitAll())
	                .exceptionHandling().accessDeniedPage("/access-denied");
	        return http.build();
	    }

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
	
	
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(korisnikService);
    }
}