package com.ismailjacoby.jobtrackerapi.model.request;

import com.ismailjacoby.jobtrackerapi.model.enums.ApplicationStatus;
import com.ismailjacoby.jobtrackerapi.model.enums.JobSource;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record JobRequest(
        @NotBlank(message = "Job title is required.")
        @Size(max = 100, message = "Job title must be less than 100 characters.")
        String title,

        @NotBlank(message = "Company name is required.")
        @Size(max = 50, message = "Company name must be less than 50 characters.")
        String companyName,

        @NotBlank(message = "Location is required.")
        @Size(max = 100, message = "Location must be less than 100 characters.")
        String location,

        @NotBlank(message = "Job URL is required.")
        String jobUrl,

        @NotNull(message = "Job source is required.")
        JobSource jobSource,

        @NotNull(message = "Application status is required.")
        ApplicationStatus status,

        @PastOrPresent(message = "Date applied cannot be in the future.")
        LocalDate dateApplied,

        @Size(max = 100, message = "Recruiter name must be less than 100 characters.")
        String recruiterName,

        @Size(max = 100, message = "Recruiter email must be less than 100 characters.")
        String recruiterEmail,

        String recruiterPhone,

        String salary,

        @Size(max = 200, message = "Notes must be less than 200 characters.")
        String notes
) {
}
