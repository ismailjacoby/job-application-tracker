package com.ismailjacoby.jobtrackerapi.service;


import com.ismailjacoby.jobtrackerapi.model.dto.UserDTO;
import com.ismailjacoby.jobtrackerapi.model.entity.User;
import com.ismailjacoby.jobtrackerapi.model.request.PasswordRequest;
import com.ismailjacoby.jobtrackerapi.model.request.UserAdminUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserDTO> getUserById(Long id);
    Page<UserDTO> getUsers(Pageable pageable);
    void changePassword(Long id, PasswordRequest request);
    void updateUserByAdmin(Long id, UserAdminUpdateRequest request);
}
