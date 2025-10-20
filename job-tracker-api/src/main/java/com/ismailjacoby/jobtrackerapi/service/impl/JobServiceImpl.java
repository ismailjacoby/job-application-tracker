package com.ismailjacoby.jobtrackerapi.service.impl;

import com.ismailjacoby.jobtrackerapi.model.entity.Job;
import com.ismailjacoby.jobtrackerapi.model.request.JobRequest;
import com.ismailjacoby.jobtrackerapi.repository.JobRepository;
import com.ismailjacoby.jobtrackerapi.service.JobService;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public void createJob(JobRequest request) {
        Job job = new Job();
        job.setTitle(request.title());
        job.setCompanyName(request.companyName());
        job.setLocation(request.location());
        job.setJobUrl(request.jobUrl());
        job.setJobSource(request.jobSource());
        job.setStatus(request.status());
        job.setDateApplied(request.dateApplied());
        job.setRecruiterName(request.recruiterName());
        job.setRecruiterEmail(request.recruiterEmail());
        job.setRecruiterPhone(request.recruiterPhone());
        job.setSalary(request.salary());
        job.setNotes(request.notes());

        jobRepository.save(job);
    }
}
