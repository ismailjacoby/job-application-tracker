import {inject, Injectable} from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {SignupRequest} from '../../features/auth/models/signup-request';
import {Observable} from 'rxjs';
import {LoginRequest} from '../../features/auth/models/login-request';
import {AuthResponse} from '../../features/auth/models/auth-response';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = `${environment.apiUrl}/auth`;

  http = inject(HttpClient);

  signup(data: SignupRequest): Observable<string> {
    return this.http.post(`${this.apiUrl}/signup`, data, {responseType: 'text'});
  }

  login(data: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, data);
  }
}
