import {UserType} from '../entity/UserSubmit';
import {UserService} from '../service/user.service';
import {Component} from '@angular/core';
import {User} from '../entity/user.entity';

@Component({
  selector: 'app-admin-register-and-login',
  templateUrl: './admin-register-and-login.component.html',
  styleUrls: ['./admin-register-and-login.component.css']
})
export class AdminRegisterAndLoginComponent {
  userForm: User;
  model: User;

  constructor(private UserService: UserService) {
    this.userForm = new User();
    this.model = new User();
  }

// for admin login
  onSubmit(adminForm: any) {
    this.userForm.email = adminForm.value.email;
    this.userForm.password = adminForm.value.password;
    this.userForm.userType = UserType.ADMIN;
    // this.UserService.login(this.userForm).subscribe();
    this.login();
  }

//for admin sign up
  onLogIn(adminform: any) {
    this.userForm.name = adminform.value.name;
    this.userForm.username = adminform.value.username;
    this.userForm.password = adminform.value.password;
    this.userForm.email = adminform.value.email;
    this.userForm.userType = UserType.ADMIN;
    this.UserService.addUser(this.userForm).subscribe();
    this.refresh();
  }

  refresh() {
    window.location.reload();
  }

// todo
  login() {

  }
}
