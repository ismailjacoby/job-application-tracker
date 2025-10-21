package com.ismailjacoby.jobtrackerapi.controller;

import com.ismailjacoby.jobtrackerapi.model.dto.JobShortDTO;
import com.ismailjacoby.jobtrackerapi.model.request.JobRequest;
import com.ismailjacoby.jobtrackerapi.service.JobService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<String> addJob(@RequestBody @Valid JobRequest request) {
        jobService.addJob(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Job added successfully");
    }

    @GetMapping
    public ResponseEntity<List<JobShortDTO>> getJobs() {
        return ResponseEntity.ok(jobService.getJobs());
    }
}
