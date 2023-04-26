import { Component } from '@angular/core';
import { User } from '../entity/user.entity';
import { HttpClient } from '@angular/common/http';
import { UserType } from '../entity/UserSubmit';
import { UserService } from '../service/user.service';
@Component({
  selector: 'app-admin-sign-up',
  templateUrl: './admin-sign-up.component.html',
  styleUrls: ['./admin-sign-up.component.css']
})
export class AdminSignUpComponent {

  userForm: User;
  model: User;
  constructor(private userService: UserService) {
    this.userForm = new User();
    this.model = new User();
  }

  onSubmit(adminform: any) {
    this.userForm.name = adminform.value.name;
    this.userForm.username = adminform.value.username;
    this.userForm.password = adminform.value.password;
    this.userForm.email = adminform.value.email;
    this.userForm.userType = UserType.ADMIN;
    this.userService.addUser(this.userForm).subscribe();
    this.refresh();
  }
  refresh() {
    window.location.reload();
  }
}
