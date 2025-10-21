package com.ismailjacoby.jobtrackerapi.service;

import com.ismailjacoby.jobtrackerapi.model.dto.JobShortDTO;
import com.ismailjacoby.jobtrackerapi.model.request.JobRequest;

import java.util.List;

public interface JobService {
    void addJob(JobRequest request);
    List<JobShortDTO> getJobs();
}
