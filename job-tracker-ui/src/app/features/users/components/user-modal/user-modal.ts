import {Component, EventEmitter, inject, Input, OnChanges, Output, SimpleChanges} from '@angular/core';
import {UserService} from '../../services/user.service';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {User} from '../../models/user.model';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-user-modal',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './user-modal.html',
  styleUrl: './user-modal.css'
})
export class UserModal implements OnChanges{
  @Input() userId: number | null = null;
  @Output() close = new EventEmitter<void>();
  @Output() userUpdated = new EventEmitter<void>();

  private userService = inject(UserService);
  private formBuilder = inject(FormBuilder);

  loading = false;
  user: User | null = null;
  editForm!: FormGroup;

  roles = ['USER', 'ADMIN'];

  ngOnChanges(changes: SimpleChanges) {
    if (changes['userId'] && this.userId) {
      this.loadUser();
    }
  }

  loadUser() {
    this.loading = true;
    this.userService.getUserById(this.userId!).subscribe({
      next: (user) => {
        this.user = user;
        this.initForm(user);
        this.loading = false;
      },
      error: () => {
        this.loading = false;
      },
    });
  }

  initForm(user: User) {
    this.editForm = this.formBuilder.group({
      role: [user.role, Validators.required],
      enabled: [user.enabled, Validators.required],
    });
  }

  onSubmit() {
    if (!this.userId || this.editForm.invalid) return;

    this.loading = true;
    this.userService.updateUserByAdmin(this.userId, this.editForm.value).subscribe({
      next: () => {
        this.loading = false;
        this.userUpdated.emit();
        this.close.emit();
      },
      error: () => {
        this.loading = false;
      },
    });
  }

}
