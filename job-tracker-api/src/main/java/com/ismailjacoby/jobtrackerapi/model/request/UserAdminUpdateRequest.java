package com.ismailjacoby.jobtrackerapi.model.request;

import com.ismailjacoby.jobtrackerapi.model.enums.UserRole;
import jakarta.validation.constraints.NotNull;

public record UserAdminUpdateRequest(
        @NotNull(message = "User role is required.")
        UserRole role,

        @NotNull(message = "Enabled status is required.")
        boolean enabled
) {
}
