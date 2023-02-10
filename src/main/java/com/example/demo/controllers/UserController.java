package com.example.demo.controllers;

import com.example.demo.configurations.JwtTokenUtil;
import com.example.demo.dtos.UserDTO;
import com.example.demo.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    public UserController(UserService userService,
                          JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public Flux<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/")
    public Mono<UserDTO> updateUser(@RequestBody UserDTO userDTO, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        Tuple2<Long, String> userIdAndRoleFromToken = jwtTokenUtil.getUserIdAndRoleFromToken(token);
        return Mono.just(userService.updateUser(userDTO, userIdAndRoleFromToken));
    }
}
