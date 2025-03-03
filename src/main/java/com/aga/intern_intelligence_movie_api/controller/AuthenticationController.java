package com.aga.intern_intelligence_movie_api.controller;

import com.aga.intern_intelligence_movie_api.model.JwtRequest;
import com.aga.intern_intelligence_movie_api.model.JwtResponse;
import com.aga.intern_intelligence_movie_api.service.impl.JwtServiceImpl;
import com.aga.intern_intelligence_movie_api.service.impl.SecurityServiceImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtServiceImpl jwtService;
    private final SecurityServiceImpl securityService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("Incorrect username or password");
        }
        final UserDetails userDetails = securityService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
}
