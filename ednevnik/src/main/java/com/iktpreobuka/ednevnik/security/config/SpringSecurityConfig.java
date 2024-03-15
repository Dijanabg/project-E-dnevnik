package com.iktpreobuka.ednevnik.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig {
	
	@Autowired
	private AuthenticationEntryPoint authEntryPoint;
	
	@Autowired
	private DataSource dataSource;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws
	Exception {
		http.csrf().disable()
		.authorizeRequests().anyRequest().authenticated().and()
		.httpBasic().authenticationEntryPoint(authEntryPoint);
		return http.build();
	}
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http
//			.authorizeHttpRequests(authorize -> authorize
//				.anyRequest().authenticated()
//			)
//			.formLogin(withDefaults())
//			.httpBasic(withDefaults());
//		return http.build();
//	}
	@Bean
	AuthenticationManager authenticationManagerBean(HttpSecurity http) throws
	Exception {
		AuthenticationManagerBuilder auth =
		http.getSharedObject(AuthenticationManagerBuilder.class);
		
		auth.jdbcAuthentication()
			.usersByUsernameQuery(this.usersQuery)
			.authoritiesByUsernameQuery(this.rolesQuery)
			.dataSource(this.dataSource)
			.passwordEncoder(passwordEncoder());
//			.passwordEncoder(
//					PasswordEncoderFactories.createDelegatingPasswordEncoder()
//			);
		return auth.build();
	}
}
