package com.ismailjacoby.jobtrackerapi.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public record LoginRequest(
        @NotBlank(message = "Email is required.")
        @Email(message =  "Email should be valid.")
        String email,

        @NotBlank(message = "Password is required.")
        @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[_#?!@=$ %^&*-]).{8,}$",
                message = "Password must be at least 8 characters long, include one uppercase letter, one lowercase letter, one number and one special character")
        String password
) {
}
