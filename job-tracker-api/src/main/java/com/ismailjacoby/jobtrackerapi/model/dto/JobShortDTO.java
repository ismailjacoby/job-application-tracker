package com.ismailjacoby.jobtrackerapi.model.dto;

import com.ismailjacoby.jobtrackerapi.model.entity.Job;
import com.ismailjacoby.jobtrackerapi.model.enums.ApplicationStatus;
import com.ismailjacoby.jobtrackerapi.model.enums.JobSource;

import java.time.LocalDate;

public record JobShortDTO (
        Long id,
        String title,
        String companyName,
        String location,
        LocalDate dateApplied,
        String salary,
        ApplicationStatus status,
        JobSource jobSource,
        String jobUrl)
{

    public static JobShortDTO fromEntity(Job job){
        return new JobShortDTO(
                job.getId(),
                job.getTitle(),
                job.getCompanyName(),
                job.getLocation(),
                job.getDateApplied(),
                job.getSalary(),
                job.getStatus(),
                job.getJobSource(),
                job.getJobUrl()
        );
    }
}
