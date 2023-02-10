package com.example.demo.services;

import com.example.demo.configurations.JwtTokenUtil;
import com.example.demo.dtos.AuthRequest;
import com.example.demo.dtos.AuthResponse;
import com.example.demo.enums.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SSOService implements UserDetailsService {
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;

    private final PasswordEncoder passwordEncoder;


    public SSOService(UserRepository userRepository,
                      JwtTokenUtil jwtTokenUtil,
                      PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
    }
    public AuthResponse login(AuthRequest authenticationRequest) {
        String username = authenticationRequest.getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with name cannot be found"));

        String password = user.getPassword();
        if (passwordEncoder.matches(authenticationRequest.getPassword(), password)) {
            return getResponseEntity(authenticationRequest);
        } else {
            throw new BadCredentialsException("invalid credentials");
        }
    }

    private AuthResponse getResponseEntity(AuthRequest authRequest) {
        final User userDetails = loadUserByUsername(authRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return new AuthResponse(token);
    }

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with name cannot be found"));
    }

    public AuthResponse register(AuthRequest authenticationRequest) {
        User user = User.builder()
                .role(Role.ROLE_CLIENT)
                .username(authenticationRequest.getUsername())
                .password(passwordEncoder.encode(authenticationRequest.getPassword()))
                .build();
        userRepository.save(user);
        return getResponseEntity(authenticationRequest);
    }
}
