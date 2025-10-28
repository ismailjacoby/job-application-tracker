import { inject, Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import {catchError, Observable, throwError} from 'rxjs';
import {environment} from '../../../../environments/environment';
import {Job, JobShort} from '../models/job.model';

@Injectable({
  providedIn: 'root',
})
export class JobService {
  private apiUrl = `${environment.apiUrl}/jobs`;

  http = inject(HttpClient);

  addJob(jobData: Job): Observable<string> {
    return this.http.post(this.apiUrl, jobData, {
      responseType: 'text',
    });
  }

  getJobById(id: number): Observable<Job> {
    return this.http.get<Job>(`${this.apiUrl}/${id}`);
  }

  getJobs(): Observable<JobShort[]> {
    return this.http.get<JobShort[]>(this.apiUrl).pipe(
      catchError((error) => {
        console.error('Error fetching jobs:', error);
        return throwError(() => error);
      })
    );
  }

  updateJob(id: number, data: Partial<Job>): Observable<Job> {
    return this.http.put<Job>(`${this.apiUrl}/${id}`, data);
  }

}
