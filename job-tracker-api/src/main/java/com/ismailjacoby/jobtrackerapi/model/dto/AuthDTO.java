package com.ismailjacoby.jobtrackerapi.model.dto;

import com.ismailjacoby.jobtrackerapi.model.enums.UserRole;
import lombok.Builder;

@Builder
public record AuthDTO(
        String username,
        String token,
        UserRole role
) {
}
