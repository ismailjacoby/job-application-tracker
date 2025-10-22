import {Component, inject, OnInit} from '@angular/core';
import {JobShort} from '../../models/job.model';
import {JobService} from '../../services/job-service';
import {CommonModule} from '@angular/common';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-job-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './job-list.html',
  styleUrl: './job-list.css'
})
export class JobList implements OnInit{
  jobs: JobShort[] = [];
  errorMessage = '';
  filteredJobs: JobShort[] = [];
  loading = true;
  searchQuery = '';

  jobService = inject(JobService);

  ngOnInit() {
    this.jobService.getJobs().subscribe({
      next: (data) => {
        this.jobs = data;
        this.filteredJobs = data;
        this.loading = false;
      }, error: (error) => {
        this.errorMessage = 'Failed to load jobs.';
        this.loading = false;
      }
      }
    )
  }

  onSearch(query: string) {
    this.searchQuery = query.toLowerCase();
    this.filteredJobs = this.jobs.filter(job =>
      job.title.toLowerCase().includes(this.searchQuery) ||
      job.companyName.toLowerCase().includes(this.searchQuery) ||
      job.location.toLowerCase().includes(this.searchQuery)
    );
  }
}
