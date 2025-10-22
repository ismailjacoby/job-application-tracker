import {inject, Injectable} from '@angular/core';
import {environment} from '../../../../environments/environment';
import {HttpClient, HttpParams } from '@angular/common/http';
import {Observable} from 'rxjs';
import {PageResponse, User} from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = `${environment.apiUrl}/users`;

  http = inject(HttpClient);

  getUsers(
    page: number = 0,
    size: number = 20,
    sortBy: string = 'id',
    direction: string = 'asc'
  ): Observable<PageResponse<User>>{
    const params = new HttpParams()
      .set('page', page)
      .set('size', size)
      .set('sort', sortBy)
      .set('direction', direction);

    return this.http.get<PageResponse<User>>(this.apiUrl, { params });
  }
}
