import {inject, Injectable} from '@angular/core';
import {environment} from '../../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PdfService {
  private apiUrl = `${environment.apiUrl}/pdf/generate`;

  http = inject(HttpClient);

  downloadPDF(): Observable<Blob> {
    return this.http.get(this.apiUrl, {responseType: 'blob'});
  }
}
