package com.learening.testing.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learening.testing.model.AppUser;
import com.learening.testing.model.AuthRequest;
import com.learening.testing.repository.UserRepository;
import com.learening.testing.util.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication APIs")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository appUserRepository;
    
   @Operation(summary = "Login user and return JWT token")
    @PostMapping("/login")
   public ResponseEntity<?> login(@RequestBody AuthRequest request, HttpSession session) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        String token = jwtUtil.generateToken(request.getUsername());
        session.setAttribute("token", token);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("sessionId", session.getId());
        return ResponseEntity.ok(response);
    }



   @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {
        if (appUserRepository.findByUsername(request.getUsername()).isPresent()) {
            return "User already exists";
        }

        AppUser newUser = new AppUser();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(request.getPassword()); // In real apps, hash this
        appUserRepository.save(newUser);

        return "User registered successfully";
    }
}

