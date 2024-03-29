package com.iktpreobuka.ednevnik.security.config;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
	@Override
	public void afterPropertiesSet() {
		setRealmName("EDnevnik");
		super.afterPropertiesSet();
	}
	
	@Override
	public void commence(HttpServletRequest request,
	HttpServletResponse response, AuthenticationException authEx)
	throws IOException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("{\"message\":\"Neuspešna autentifikacija: Pogrešno korisničko ime ili lozinka\"}");
        out.flush();
	}
}
