package br.com.wk.testejava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wk.testejava.dto.AuthenticationRequest;
import br.com.wk.testejava.dto.AuthenticationResponse;
import br.com.wk.testejava.service.AuthenticationService;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody AuthenticationRequest request) {
        return new ResponseEntity<>(authenticationService.generateJWTTokenUser(request), HttpStatus.OK);
    }
    
}
