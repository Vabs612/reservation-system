package com.vaibhav.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	String[] staticResources = { "/events/**", "/images/**", "/fonts/**", "/scripts/**", "/assets/**", "/forms/**" };

	@Bean
	UserDetailsService service() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(service());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeHttpRequests((requests) -> requests.requestMatchers("/registration/**").permitAll()
						.requestMatchers(staticResources).permitAll().requestMatchers("/login/**").permitAll()
						.requestMatchers("/").permitAll().requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
						.requestMatchers("/admin/**").hasAnyRole("ADMIN").anyRequest().authenticated())
				.formLogin((form) -> form.loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/user/")
						.permitAll().usernameParameter("name"))
				.logout((logout) -> logout.permitAll()).exceptionHandling().accessDeniedPage("/access-denied");
		return http.build();

	}
}
