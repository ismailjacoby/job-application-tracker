import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Job } from '../models/job.model';
import { Observable } from 'rxjs';

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
}
