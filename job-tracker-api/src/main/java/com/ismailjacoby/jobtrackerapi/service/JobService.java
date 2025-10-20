package com.ismailjacoby.jobtrackerapi.service;

import com.ismailjacoby.jobtrackerapi.model.request.JobRequest;

public interface JobService {
    void addJob(JobRequest request);
}
