package com.ismailjacoby.jobtrackerapi.service.impl;

import com.ismailjacoby.jobtrackerapi.exception.DuplicateException;
import com.ismailjacoby.jobtrackerapi.model.entity.User;
import com.ismailjacoby.jobtrackerapi.model.enums.UserRole;
import com.ismailjacoby.jobtrackerapi.model.request.SignupRequest;
import com.ismailjacoby.jobtrackerapi.repository.UserRepository;
import com.ismailjacoby.jobtrackerapi.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signup(SignupRequest request) {
        String email = request.email().toLowerCase().trim();

        if(userRepository.existsByEmail(email)){
            throw new DuplicateException("User with email " + email + " already exists");
        }

        User user = new User();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(UserRole.USER);
        user.setEnabled(true);
        userRepository.save(user);
    }
}
