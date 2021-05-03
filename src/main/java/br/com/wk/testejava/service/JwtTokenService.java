package br.com.wk.testejava.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.wk.testejava.entity.Role;
import br.com.wk.testejava.entity.User;
import br.com.wk.testejava.security.TokenAuthenticationService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenService {

    private String secret;
    private Long expiration;

    public JwtTokenService(@Value("${jwt.expiration}") Long expiration) {
        this.secret = TokenAuthenticationService.SECRET;
        this.expiration = expiration;
    }

    public String generateTokenUser(User user) {
        final Date createdDate = new Date();
        final Date expirationDate = calculateExpirationDate(createdDate);

        List<String> userRoles = new ArrayList<>();
        HashMap<String, Object> claims = new HashMap<>();

        for(Role role: user.getProfile().getRoles()){
            userRoles.add(role.getNmRole());
        }

        claims.put("rol", userRoles);
        claims.put("idUser", user.getIdUser());
        claims.put("nmUser", user.getNmUser());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getDsEmail())
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration);
    }
}
