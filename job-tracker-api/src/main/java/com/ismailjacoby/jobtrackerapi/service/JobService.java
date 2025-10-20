package com.ismailjacoby.jobtrackerapi.service;

import com.ismailjacoby.jobtrackerapi.model.request.JobRequest;

public interface JobService {
    void createJob(JobRequest request);
}
