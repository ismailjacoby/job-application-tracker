package com.ismailjacoby.jobtrackerapi.model.dto;

import com.ismailjacoby.jobtrackerapi.model.enums.ApplicationStatus;
import com.ismailjacoby.jobtrackerapi.model.enums.JobSource;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record JobDTO(
        Long id,
        String title,
        String companyName,
        String location,
        String jobUrl,
        JobSource jobSource,
        ApplicationStatus status,
        LocalDate dateApplied,
        String recruiterName,
        String recruiterEmail,
        String recruiterPhone,
        String salary,
        String notes,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
