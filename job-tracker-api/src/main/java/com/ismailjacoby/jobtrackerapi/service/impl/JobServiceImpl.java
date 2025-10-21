package com.ismailjacoby.jobtrackerapi.service.impl;

import com.ismailjacoby.jobtrackerapi.exception.NotFoundException;
import com.ismailjacoby.jobtrackerapi.model.dto.JobDTO;
import com.ismailjacoby.jobtrackerapi.model.dto.JobShortDTO;
import com.ismailjacoby.jobtrackerapi.model.entity.Job;
import com.ismailjacoby.jobtrackerapi.model.request.JobRequest;
import com.ismailjacoby.jobtrackerapi.repository.JobRepository;
import com.ismailjacoby.jobtrackerapi.service.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<JobDTO> getJobById(Long id) {
        return jobRepository.findById(id)
                .map(JobDTO::fromEntity);
    }

    @Override
    public List<JobShortDTO> getJobs() {
        return jobRepository.findAll().stream()
                .map(JobShortDTO::fromEntity)
                .toList();
    }

    @Override
    public JobDTO updateJob(Long id, JobRequest request) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Job with id " + id + " not found."));

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

        Job updatedJob = jobRepository.save(job);

        return JobDTO.fromEntity(updatedJob);
    }

    @Override
    public void deleteJobById(Long id) {
        if (!jobRepository.existsById(id)) {
            throw new NotFoundException("Job with id " + id + " not found.");
        }
        jobRepository.deleteById(id);
    }
}
