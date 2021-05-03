package br.com.wk.testejava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.wk.testejava.dto.AuthenticationRequest;
import br.com.wk.testejava.dto.AuthenticationResponse;
import br.com.wk.testejava.exception.UnauthorizedException;
import br.com.wk.testejava.repository.UserRepository;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private JwtTokenService jwtTokenService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthenticationResponse generateJWTTokenUser(AuthenticationRequest authentication) {

        return userRepository.findFirstByDsEmailIgnoreCase(authentication.getUsername())
                .filter(user ->  passwordEncoder.matches(authentication.getPassword(), user.getDsPassword()))
                .map(user -> new AuthenticationResponse(jwtTokenService.generateTokenUser(user)))
                .orElseThrow(() ->  new UnauthorizedException());

    }
    
}
