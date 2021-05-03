package br.com.wk.testejava.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		
		List<String> allowed = new ArrayList<String>();
		allowed.add(CorsConfiguration.ALL);
		corsConfiguration.setAllowedHeaders(allowed);
		corsConfiguration.setAllowedMethods(allowed);
		corsConfiguration.setAllowedOrigins(allowed);
		
		httpSecurity.cors().configurationSource(request -> corsConfiguration);

		httpSecurity.csrf().disable().authorizeRequests()

		.antMatchers(HttpMethod.POST, "/login").permitAll()

		.antMatchers(HttpMethod.GET,	"/pessoa/**").hasRole("PESSOA_FIND")
		.antMatchers(HttpMethod.POST,	"/pessoa").hasRole("PESSOA_SAVE")
		.antMatchers(HttpMethod.POST,	"/pessoa/json").hasRole("PESSOA_SAVE")
	

				.antMatchers("/user/client",
						 "/v2/api-docs",
						 "/configuration/ui",
						 "/swagger-resources/**",
						 "/configuration/security",
						 "/swagger-ui.html",
						 "/webjars/**",
	                     "/actuator/**",
	                     "/resources/**").permitAll()

			.anyRequest().authenticated()
			.and()

			// filtra outras requisições para verificar a presença do JWT no header
			.addFilterBefore(new JWTAuthenticationFilter(),
	                UsernamePasswordAuthenticationFilter.class);
	}
}
