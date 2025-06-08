package com.sanadapp.sanad_backend.service;

import com.sanadapp.sanad_backend.dto.AuthRequest;
import com.sanadapp.sanad_backend.dto.AuthResponse;
import com.sanadapp.sanad_backend.dto.RegisterRequest;
import com.sanadapp.sanad_backend.entity.User;
import com.sanadapp.sanad_backend.repository.UserRepository;
import com.sanadapp.sanad_backend.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthResponse register(RegisterRequest request) {
        // Check if user already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists with this email");
        }

        // Create new user
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .totalDonated(0.0)
                .donationsCount(0)
                .joinedAt(Instant.now())
                .lastLoginAt(Instant.now())
                .build();

        user = userRepository.save(user);

        // Generate tokens
        String token = jwtTokenProvider.generateToken(user.getId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getId());

        return buildAuthResponse(user, token, refreshToken);
    }

    public AuthResponse login(AuthRequest request) {
        // Find user by email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        // Check password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        // Update last login
        user.setLastLoginAt(Instant.now());
        userRepository.save(user);

        // Generate tokens
        String token = jwtTokenProvider.generateToken(user.getId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getId());

        return buildAuthResponse(user, token, refreshToken);
    }


    public void logout(String token) {
        // In a production app, you might want to blacklist the token
        // For now, we'll just let the token expire naturally
    }

    private AuthResponse buildAuthResponse(User user, String token, String refreshToken) {
        AuthResponse.UserInfo userInfo = AuthResponse.UserInfo.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .photoURL(user.getPhotoURL())
                .totalDonated(user.getTotalDonated())
                .donationsCount(user.getDonationsCount())
                .build();

        return AuthResponse.builder()
                .token(token)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .expiresIn(3600L) // 1 hour
                .user(userInfo)
                .build();
    }
}