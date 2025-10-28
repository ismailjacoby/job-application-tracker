import {Component, EventEmitter, inject, Input, OnChanges, Output, SimpleChanges} from '@angular/core';
import {JobService} from '../../services/job-service';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {Job} from '../../models/job.model';
import {CommonModule} from '@angular/common';
import {JobSource} from '../../models/job-source.enum';
import {ApplicationStatus} from '../../models/application-status.enum';

@Component({
  selector: 'app-job-modal',
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './job-modal.html',
  styleUrl: './job-modal.css'
})
export class JobModal implements OnChanges{
  @Input() jobId: number | null = null;
  @Output() close = new EventEmitter();
  @Output() jobUpdated = new EventEmitter();

  jobService = inject(JobService);
  formBuilder = inject(FormBuilder);

  loading = false;
  job: Job | null = null;
  editForm!: FormGroup;

  statuses = Object.values(ApplicationStatus);
  sources = Object.values(JobSource);

  ngOnChanges(changes: SimpleChanges) {
    if (changes['jobId'] && this.jobId) {
      this.loadJob();
    }
  }


  loadJob() {
    this.loading = true;
    this.jobService.getJobById(this.jobId!).subscribe({
      next: (job) => {
        this.job = job;
        this.loading = false;
        this.initForm(job);
      },
      error: () => {
        this.loading = false;
      },
    });
  }

  initForm(job: Job) {
    this.editForm = this.formBuilder.group({
      title: [job.title, Validators.required],
      companyName: [job.companyName, Validators.required],
      location: [job.location, Validators.required],
      jobUrl: [job.jobUrl],
      jobSource: [job.jobSource],
      status: [job.status],
      dateApplied: [job.dateApplied],
      recruiterName: [job.recruiterName],
      recruiterEmail: [job.recruiterEmail],
      recruiterPhone: [job.recruiterPhone],
      salary: [job.salary],
      notes: [job.notes],
    });
  }

  onSubmit() {
    if (!this.jobId || this.editForm.invalid) return;

    this.loading = true;
    this.jobService.updateJob(this.jobId, this.editForm.value).subscribe({
      next: () => {
        this.loading = false;
        this.jobUpdated.emit();
        this.close.emit();
      },
      error: () => {
        this.loading = false;
      },
    });
  }

}
