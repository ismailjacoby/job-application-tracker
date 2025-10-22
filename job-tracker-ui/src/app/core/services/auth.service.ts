import {computed, inject, Injectable, signal} from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {SignupRequest} from '../../features/auth/models/signup-request';
import {Observable, tap} from 'rxjs';
import {LoginRequest} from '../../features/auth/models/login-request';
import {AuthResponse} from '../../features/auth/models/auth-response';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = `${environment.apiUrl}/auth`;
  private http = inject(HttpClient);
  private router = inject(Router);

  // Signals for reactive state
  private _token = signal<string | null>(localStorage.getItem('token'));
  private _username = signal<string | null>(localStorage.getItem('username'));
  private _role = signal<string | null>(localStorage.getItem('role'));


  // Computed signals for easy reactive access
  isAuthenticated = computed(() => !!this._token());
  userRole = computed(() => this._role());
  username = computed(() => this._username());

  /**
   * Registers a new user
   */
  signup(data: SignupRequest): Observable<string> {
    return this.http.post(`${this.apiUrl}/signup`, data, {responseType: 'text'});
  }

  /**
   * Logs in a user and updates signals + localStorage
   */
  login(data: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, data).pipe(
      tap((response) => {
        this._token.set(response.token);
        this._username.set(response.username);
        this._role.set(response.role);

        localStorage.setItem('token', response.token);
        localStorage.setItem('username', response.username);
        localStorage.setItem('role', response.role);
      })
    );
  }

  /**
   * Logs out and resets state
   */
  logout(): void {
    this._token.set(null);
    this._username.set(null);
    this._role.set(null);

    localStorage.removeItem('token');
    localStorage.removeItem('username');
    localStorage.removeItem('role');

    this.router.navigate(['/home']);
  }

  /**
   * Returns current token (for interceptors)
   */
  getToken(): string | null {
    return this._token();
  }
}
