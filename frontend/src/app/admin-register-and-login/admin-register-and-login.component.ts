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

  constructor() {
    this.userForm = new User();
    this.model = new User();
  }

// for admin login
  onSubmit(adminForm: any) {
    this.userForm.email = adminForm.value.email;
    this.userForm.password = adminForm.value.password;
    this.userForm.userType = UserType.ADMIN;
    //this.login();
  }

  refresh() {
    window.location.reload();
  }
}
