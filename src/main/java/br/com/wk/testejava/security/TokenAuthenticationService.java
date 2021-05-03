package br.com.wk.testejava.security;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Jwts;

public class TokenAuthenticationService {

	// EXPIRATION_TIME = 10 dias
	static final long EXPIRATION_TIME = 860_000_000;
	public static final String SECRET = "5034618516765b232cdd17354d251d58695dbc98";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";

	@SuppressWarnings("unchecked")
	static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);


		if (token != null) {

			// faz parse do token
			String user = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody()
					.getSubject();

			List<String> roles = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody()
					.get("rol", ArrayList.class);

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
			}
		}
		return null;
	}
	
	public static Integer getIdUser(String token) {
		
		Integer user = Jwts.parser()
				.setSigningKey(SECRET)
				.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
				.getBody()
				.get("idUser", Integer.class);
		
		return user; 
	}
	
}
