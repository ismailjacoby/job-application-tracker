import {Component, inject, OnInit} from '@angular/core';
import {JobShort} from '../../models/job.model';
import {JobService} from '../../services/job-service';
import {CommonModule} from '@angular/common';
import {RouterLink} from '@angular/router';
import {PdfService} from '../../services/pdf-service';
import {JobModal} from '../job-modal/job-modal';

@Component({
  selector: 'app-job-list',
  standalone: true,
  imports: [CommonModule, RouterLink, JobModal],
  templateUrl: './job-list.html',
  styleUrl: './job-list.css'
})
export class JobList implements OnInit{
  jobs: JobShort[] = [];
  errorMessage = '';
  filteredJobs: JobShort[] = [];
  loading = true;
  searchQuery = '';
  selectedJobId: number | null = null;

  jobService = inject(JobService);
  pdfService = inject(PdfService);

  ngOnInit() {
    this.loadJobs();
  }

  loadJobs() {
    this.loading = true;
    this.jobService.getJobs().subscribe({
      next: (data) => {
        this.jobs = data;
        this.filteredJobs = data;
        this.loading = false;
      },
      error: () => {
        this.errorMessage = 'Failed to load jobs.';
        this.loading = false;
      },
    });
  }

  onSearch(query: string) {
    this.searchQuery = query.toLowerCase();
    this.filteredJobs = this.jobs.filter(job =>
      job.title.toLowerCase().includes(this.searchQuery) ||
      job.companyName.toLowerCase().includes(this.searchQuery) ||
      job.location.toLowerCase().includes(this.searchQuery)
    );
  }

  downloadPDF(){
    this.pdfService.downloadPDF().subscribe({
      next: (blob) => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'job_applications.pdf';
        a.click();
        window.URL.revokeObjectURL(url);
      }, error: (error) => {
        console.error('Error downloading PDF:', error);
        this.errorMessage = 'Failed to download report. Please try again.';
      }
    })
  }

  openJobModal(id: number) {
    this.selectedJobId = id;
  }



  onJobUpdated() {
    this.loadJobs();
    this.selectedJobId = null;
  }
}
