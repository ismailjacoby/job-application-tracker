package com.ismailjacoby.jobtrackerapi.service.impl;

import com.ismailjacoby.jobtrackerapi.exception.NotFoundException;
import com.ismailjacoby.jobtrackerapi.exception.PasswordMismatchException;
import com.ismailjacoby.jobtrackerapi.model.dto.UserShortDTO;
import com.ismailjacoby.jobtrackerapi.model.entity.User;
import com.ismailjacoby.jobtrackerapi.model.request.PasswordRequest;
import com.ismailjacoby.jobtrackerapi.repository.UserRepository;
import com.ismailjacoby.jobtrackerapi.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserShortDTO> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserShortDTO::fromEntity)
                .toList();
    }

    @Override
    public void changePassword(Long id, PasswordRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found."));

        if (!passwordEncoder.matches(request.oldPassword(), user.getPassword())) {
            throw new PasswordMismatchException("Old password is incorrect.");
        }

        if (passwordEncoder.matches(request.newPassword(), user.getPassword())) {
            throw new IllegalArgumentException("New password must differ from old password.");
        }

        if (!request.newPassword().equals(request.confirmNewPassword())) {
            throw new PasswordMismatchException("New passwords do not match.");
        }

        user.setPassword(passwordEncoder.encode(request.newPassword()));
        userRepository.save(user);
    }
}
