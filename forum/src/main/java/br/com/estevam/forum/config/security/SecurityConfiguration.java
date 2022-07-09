package br.com.estevam.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration { 
	
	@Autowired
	private AutenticacaoService autenticacaoService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		return http
					.authorizeHttpRequests()
					.antMatchers(HttpMethod.GET ,"/topicos/**").permitAll()
					.anyRequest().authenticated()
				.and()
					.formLogin()
				.and()
					.userDetailsService(autenticacaoService)
				.build();
						
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
}
