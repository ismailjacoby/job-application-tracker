package com.ismailjacoby.jobtrackerapi.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignupRequest(
        @NotBlank(message = "First name is required.")
        @Size(max = 50, message = "First name cannot exceed 50 characters.")
        String firstName,

        @NotBlank(message = "Last name is required.")
        @Size(max = 50, message = "Last name cannot exceed 50 characters.")
        String lastName,

        @NotBlank(message = "Email is required.")
        @Email(message =  "Email should be valid.")
        @Size(max = 50, message = "Email cannot exceed 50 characters.")
        String email,

        @NotBlank(message = "Password is required.")
        @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[_#?!@=$ %^&*-]).{8,}$",
                message = "Password must be at least 8 characters long, include one uppercase letter, one lowercase letter, one number and one special character")
        String password
) {
}
