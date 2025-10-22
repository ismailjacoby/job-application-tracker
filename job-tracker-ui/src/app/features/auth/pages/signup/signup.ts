import {Component, inject} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {AuthService} from '../../../../core/services/auth.service';
import {Router, RouterLink} from '@angular/router';
import {CommonModule} from '@angular/common';
import {catchError, throwError} from 'rxjs';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './signup.html',
  styleUrl: './signup.css'
})
export class Signup {
  signupForm: FormGroup = new FormGroup({});
  loading = false;
  errorMessage = '';
  successMessage = '';

  formBuilder = inject(FormBuilder);
  authService  = inject(AuthService);
  router = inject(Router);

  constructor() {
    this.signupForm = this.formBuilder.group({
      firstName: ['', [Validators.required, Validators.maxLength(50)]],
      lastName: ['', [Validators.required, Validators.maxLength(50)]],
      email: ['', [Validators.required, Validators.email]],
      password: [
        '',
        [
          Validators.required,
          Validators.pattern(/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[_#?!@=$ %^&*-]).{8,}$/),
        ],
      ],
    });
  }

  onSubmit() {
    if (this.signupForm.invalid) {
      return;
    }

    this.loading = true;
    this.errorMessage = '';
    this.successMessage = '';

    this.authService.signup(this.signupForm.value).pipe(
      catchError((error) => {
        this.loading = false;
        this.errorMessage = error?.error?.message || 'An unexpected error occurred. Please try again.';
        return throwError(() => error);
      })
    ).subscribe({next: (response) => {
        this.loading = false;
        this.successMessage = response;
        setTimeout(() => this.router.navigate(['/auth/login']), 1500);
        },
    });
  }

}
