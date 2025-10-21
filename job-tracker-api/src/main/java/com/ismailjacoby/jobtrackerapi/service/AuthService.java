package com.ismailjacoby.jobtrackerapi.service;

import com.ismailjacoby.jobtrackerapi.model.dto.AuthDTO;
import com.ismailjacoby.jobtrackerapi.model.request.LoginRequest;
import com.ismailjacoby.jobtrackerapi.model.request.SignupRequest;

public interface AuthService {
    AuthDTO login(LoginRequest request);
    void signup(SignupRequest request);
}
