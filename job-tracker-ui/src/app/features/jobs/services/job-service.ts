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

  getJobs(): Observable<JobShort[]> {
    return this.http.get<JobShort[]>(this.apiUrl).pipe(
      catchError((error) => {
        console.error('Error fetching jobs:', error);
        return throwError(() => error);
      })
    );
  }
}
