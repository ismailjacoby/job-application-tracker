package com.ismailjacoby.jobtrackerapi.model.dto;

import com.ismailjacoby.jobtrackerapi.model.entity.Job;
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
        String notes
) {
    public static JobDTO fromEntity(Job job){
        return new JobDTO(
                job.getId(),
                job.getTitle(),
                job.getCompanyName(),
                job.getLocation(),
                job.getJobUrl(),
                job.getJobSource(),
                job.getStatus(),
                job.getDateApplied(),
                job.getRecruiterName(),
                job.getRecruiterEmail(),
                job.getRecruiterPhone(),
                job.getSalary(),
                job.getNotes()
        );
    }
}
