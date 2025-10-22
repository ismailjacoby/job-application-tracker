import {Component, inject, OnInit} from '@angular/core';
import {User} from '../../models/user.model';
import {UserService} from '../../services/user.service';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-user-list',
  imports: [CommonModule],
  templateUrl: './user-list.html',
  styleUrl: './user-list.css'
})
export class UserList implements OnInit{
  users: User[] = [];
  currentPage = 0;
  totalPages = 0;

  userService = inject(UserService);


  ngOnInit() {
    this.loadUsers();
  }

  loadUsers(page: number = 0): void {
    this.userService.getUsers(page).subscribe((response) => {
      this.users = response.content;
      this.currentPage = response.number;
      this.totalPages = response.totalPages;
    })
  }

  nextPage() {
    if (this.currentPage < this.totalPages - 1) {
      this.loadUsers(this.currentPage + 1);
    }
  }

  prevPage() {
    if (this.currentPage > 0) {
      this.loadUsers(this.currentPage - 1);
    }
  }

  editUser(id: number): void {
    console.log('Edit user', id);
  }

}
