package com.ismailjacoby.jobtrackerapi.service;

import com.ismailjacoby.jobtrackerapi.model.request.SignupRequest;

public interface AuthService {
    void signup(SignupRequest request);
}
