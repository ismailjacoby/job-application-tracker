package com.ismailjacoby.jobtrackerapi.service;

import com.ismailjacoby.jobtrackerapi.model.dto.JobDTO;
import com.ismailjacoby.jobtrackerapi.model.dto.JobShortDTO;
import com.ismailjacoby.jobtrackerapi.model.request.JobRequest;

import java.util.List;
import java.util.Optional;

public interface JobService {
    void addJob(JobRequest request);
    Optional<JobDTO> getJobById(Long id);
    List<JobShortDTO> getJobs();
}
