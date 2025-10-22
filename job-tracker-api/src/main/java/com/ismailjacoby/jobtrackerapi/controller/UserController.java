package com.ismailjacoby.jobtrackerapi.controller;

import com.ismailjacoby.jobtrackerapi.model.dto.UserDTO;
import com.ismailjacoby.jobtrackerapi.model.request.PasswordRequest;
import com.ismailjacoby.jobtrackerapi.model.request.UserAdminUpdateRequest;
import com.ismailjacoby.jobtrackerapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @PutMapping("/password/{id}")
    public ResponseEntity<String> changePassword(@PathVariable Long id,
                                                 @RequestBody @Valid PasswordRequest request){
        userService.changePassword(id, request);
        return ResponseEntity.ok("Password changed successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> changeUserByAdmin(@PathVariable Long id,
                                                    @RequestBody @Valid UserAdminUpdateRequest request){
        userService.updateUserByAdmin(id, request);
        return ResponseEntity.ok("User updated successfully");

    }
}
