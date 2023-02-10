package com.example.demo.services;

import com.example.demo.dtos.UserDTO;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Flux<UserDTO> getAllUsers() {
        return Flux.fromIterable(userRepository.findAll())
                .flatMap(this::convertToDto);
    }

    private Mono<UserDTO> convertToDto(User user) {
        return Mono.just(UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .build());
    }

    public UserDTO updateUser(UserDTO userDTO, Tuple2<Long, String> userIdAndRoleFromToken) {
        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new UsernameNotFoundException("Following account Id doesn't exist"));
        if (isRoleAdmin(userIdAndRoleFromToken) || user.getId().equals(userIdAndRoleFromToken.getT1())) {
            updateUserFromDTO(userDTO, user);
        } else {
            throw new AuthorizationServiceException("User is not authorized for following edit");
        }
        return userDTO;
    }

    private static boolean isRoleAdmin(Tuple2<Long, String> userIdAndRoleFromToken) {
        return userIdAndRoleFromToken.getT2().toLowerCase().contains("admin");
    }

    private void updateUserFromDTO(UserDTO dto, User user) {
        if (user.getRole() != dto.getRole()) {
            user.setRole(dto.getRole());
            userRepository.save(user);
        }
    }
}
