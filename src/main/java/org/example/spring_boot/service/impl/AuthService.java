package org.example.spring_boot.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.spring_boot.entity.UserEntity;
import org.example.spring_boot.util.JwtService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.example.spring_boot.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String login(String username, String rawPassword) {
        UserEntity user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(rawPassword, user.getPasswordHash())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        // Convert UserEntity to Spring Security UserDetails
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserName())
                .password(user.getPasswordHash())
                .authorities(user.getRoles()) // your Role enum implements GrantedAuthority
                .build();

        return jwtService.generateToken(userDetails); // generates JWT
    }
}
