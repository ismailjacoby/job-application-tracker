package com.ismailjacoby.jobtrackerapi.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter @Setter
public class ErrorResponseDTO {
    private String status;
    private String message;
    private String timestamp = LocalDateTime.now().toString();

    public ErrorResponseDTO(String status, String message, HttpStatus httpStatus) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now().toString();
    }
}
