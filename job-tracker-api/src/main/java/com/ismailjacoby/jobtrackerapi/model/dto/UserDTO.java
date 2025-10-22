package com.ismailjacoby.jobtrackerapi.model.dto;

import com.ismailjacoby.jobtrackerapi.model.entity.User;
import com.ismailjacoby.jobtrackerapi.model.enums.UserRole;

public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        UserRole role,
        boolean enabled
) {
    public static UserDTO fromEntity(User user){
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole(),
                user.isEnabled()
        );
    }
}
