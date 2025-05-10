package com.example.auth.service;


import com.example.auth.Entities.Profile;
import com.example.auth.dto.AuthRequest;
import com.example.auth.repository.ProfileRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final ProfileRepository profileRepository;

    public AuthService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ResponseEntity<String> registerUser(Profile profile) {
        if (!profile.getPassword().equals(profile.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Passwords do not match!");
        }

        if (profileRepository.findByEmail(profile.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already registered!");
        }

        profileRepository.save(profile);
        return ResponseEntity.ok("User registered successfully!");
    }

    public ResponseEntity<String> loginUser(AuthRequest request) {
        Optional<Profile> user = profileRepository.findByEmail(request.getEmail());

        if (user.isPresent() && user.get().getPassword().equals(request.getPassword())) {
            return ResponseEntity.ok("Login successful!");
        }

        return ResponseEntity.badRequest().body("Invalid credentials!");
    }
}
