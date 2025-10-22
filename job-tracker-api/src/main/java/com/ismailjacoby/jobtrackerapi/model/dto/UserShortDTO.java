package com.ismailjacoby.jobtrackerapi.model.dto;

import com.ismailjacoby.jobtrackerapi.model.entity.User;
import com.ismailjacoby.jobtrackerapi.model.enums.UserRole;

public record UserShortDTO(
        String firstName,
        String lastName,
        String email,
        UserRole role,
        boolean enabled
) {
    public static UserShortDTO fromEntity(User user){
        return new UserShortDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole(),
                user.isEnabled()
        );
    }
}
