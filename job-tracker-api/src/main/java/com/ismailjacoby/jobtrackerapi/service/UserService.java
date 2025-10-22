package com.ismailjacoby.jobtrackerapi.service;


import com.ismailjacoby.jobtrackerapi.model.dto.UserShortDTO;
import com.ismailjacoby.jobtrackerapi.model.enums.UserRole;
import com.ismailjacoby.jobtrackerapi.model.request.PasswordRequest;
import com.ismailjacoby.jobtrackerapi.repository.UserRepository;

import java.util.List;

public interface UserService {
    List<UserShortDTO> getUsers();
    void changePassword(Long id, PasswordRequest request);
}
