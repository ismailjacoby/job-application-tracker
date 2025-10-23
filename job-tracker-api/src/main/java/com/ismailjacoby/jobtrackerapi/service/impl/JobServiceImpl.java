package com.ismailjacoby.jobtrackerapi.service.impl;

import com.ismailjacoby.jobtrackerapi.exception.NotFoundException;
import com.ismailjacoby.jobtrackerapi.model.dto.JobDTO;
import com.ismailjacoby.jobtrackerapi.model.dto.JobShortDTO;
import com.ismailjacoby.jobtrackerapi.model.entity.Job;
import com.ismailjacoby.jobtrackerapi.model.entity.User;
import com.ismailjacoby.jobtrackerapi.model.request.JobRequest;
import com.ismailjacoby.jobtrackerapi.repository.JobRepository;
import com.ismailjacoby.jobtrackerapi.repository.UserRepository;
import com.ismailjacoby.jobtrackerapi.service.JobService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public JobServiceImpl(JobRepository jobRepository, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createJob(JobRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found."));

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
        job.setUser(user);

        jobRepository.save(job);
    }

    @Override
    public Optional<JobDTO> getJobById(Long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Job with id " + id + " not found."));

        if(!job.getUser().getEmail().equals(email)){
            throw new AccessDeniedException("You are not authorized to view this job.");
        }

        return Optional.of(JobDTO.fromEntity(job));
    }

    @Override
    public List<JobShortDTO> getJobs() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found."));

        return jobRepository.findByUser(user)
                .stream()
                .map(JobShortDTO::fromEntity)
                .toList();
    }

    @Override
    public JobDTO updateJob(Long id, JobRequest request) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Job not found."));

        if(!job.getUser().getEmail().equals(email)){
            throw new AccessDeniedException("You are not authorized to modify this job.");
        }

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

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Job not found."));

        if(!job.getUser().getEmail().equals(email)){
            throw new AccessDeniedException("You are not authorized to delete this job.");
        }

        jobRepository.deleteById(id);
    }
}
