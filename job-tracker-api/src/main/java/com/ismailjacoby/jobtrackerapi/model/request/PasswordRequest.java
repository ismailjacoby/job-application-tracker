package com.ismailjacoby.jobtrackerapi.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PasswordRequest(
        @NotBlank(message = "Old Password is required.")
        String oldPassword,

        @NotBlank(message = "New Password is required.")
        @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[_#?!@=$ %^&*-]).{8,}$",
                message = "Password must be at least 8 characters long, include one uppercase letter, one lowercase letter, one number and one special character")
        String newPassword,

        @NotBlank(message = "Confirm New Password is required.")
        String confirmNewPassword
) {
}
