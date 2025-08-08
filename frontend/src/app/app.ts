import { Component, OnInit, signal } from '@angular/core';
import { User, UserData } from './user';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  imports: [CommonModule, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App implements OnInit {
  protected readonly title = signal('frontend');

  users: UserData[] = [];
  newUser: UserData = { name: '', email: '' };
  showUsers = false;
  constructor(private userService: User) { }

  ngOnInit() {
    this.userService.getUsers().subscribe(data => this.users = data);
  }

  addUser() {
    console.log(this.newUser);
    if (this.newUser.name && this.newUser.email) {
      this.userService.createUser(this.newUser).subscribe(user => {
        this.users.push(user);
        this.newUser = { name: '', email: '' };
      });
    }
  }

  toggleUserList() {
    this.showUsers = !this.showUsers;
  }
}
