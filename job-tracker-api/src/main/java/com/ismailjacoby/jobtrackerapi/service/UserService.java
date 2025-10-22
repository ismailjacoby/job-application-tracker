package com.ismailjacoby.jobtrackerapi.service;


import com.ismailjacoby.jobtrackerapi.model.dto.UserDTO;
import com.ismailjacoby.jobtrackerapi.model.request.PasswordRequest;
import com.ismailjacoby.jobtrackerapi.model.request.UserAdminUpdateRequest;

import java.util.List;

public interface UserService {
    List<UserDTO> getUsers();
    void changePassword(Long id, PasswordRequest request);
    void updateUserByAdmin(Long id, UserAdminUpdateRequest request);
}
