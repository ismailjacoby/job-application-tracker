import {Component, inject} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {Router, RouterLink} from '@angular/router';
import {CommonModule} from '@angular/common';
import {AuthService} from '../../../../core/services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {
  loginForm: FormGroup = new FormGroup({});
  loading = false;
  errorMessage = '';

  formBuilder = inject(FormBuilder);
  authService  = inject(AuthService);
  router = inject(Router);

  constructor() {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
    })
  }

  onSubmit(): void {
    if(this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    this.errorMessage = '';

    const credentials = this.loginForm.value;

    this.authService.login(credentials).subscribe({
      next: (response) => {
        this.router.navigate(['/app/dashboard']);
      },
      error: (error) => {
        this.loading = false;
        this.errorMessage = error?.error?.message || 'Invalid email or password!';
      }, complete: () => {
        this.loading = false;
      }
    })


  }


}
