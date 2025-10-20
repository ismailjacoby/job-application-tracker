import { Component, inject } from '@angular/core';
import { ApplicationStatus } from '../../../../core/models/application-status.enum';
import { JobSource } from '../../../../core/models/job-source.enum';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { JobService } from '../../../../core/services/job-service';

@Component({
  selector: 'app-job-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './job-form.html',
  styleUrl: './job-form.css',
})
export class JobForm {
  jobForm: FormGroup;
  isSubmitting = false;
  successMessage: String = '';
  errorMessage: String = '';

  jobService = inject(JobService);

  jobSources = Object.values(JobSource);
  applicationStatuses = Object.values(ApplicationStatus);

  constructor(private formBuilder: FormBuilder) {
    this.jobForm = this.formBuilder.group({
      title: ['', [Validators.required, Validators.maxLength(100)]],
      companyName: ['', [Validators.required, Validators.maxLength(50)]],
      location: ['', [Validators.required, Validators.maxLength(100)]],
      jobUrl: ['', [Validators.required]],
      jobSource: ['', Validators.required],
      status: ['', Validators.required],
      dateApplied: [''],
      recruiterName: [''],
      recruiterEmail: [''],
      recruiterPhone: [''],
      salary: [''],
      notes: ['', Validators.maxLength(200)],
    });
  }

  onSubmit() {
    if (this.jobForm.invalid) {
      return;
    }

    this.isSubmitting = true;

    const jobData = this.jobForm.value;

    this.jobService.addJob(jobData).subscribe({
      next: (response) => {
        this.successMessage = response;
        this.errorMessage = '';
        this.isSubmitting = false;
        this.jobForm.reset();
      },
      error: (err) => {
        this.errorMessage = err.message;
        this.successMessage = '';
        this.isSubmitting = false;
        console.log(err);
      },
    });
  }
}
