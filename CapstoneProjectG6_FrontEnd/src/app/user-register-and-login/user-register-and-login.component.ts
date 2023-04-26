import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserSubmit, UserType } from '../entity/UserSubmit';
import { UserService } from '../service/user.service';
import { User } from '../entity/user.entity';

@Component({
  selector: 'app-user-register-and-login',
  templateUrl: './user-register-and-login.component.html',
  styleUrls: ['./user-register-and-login.component.css']
})
export class UserRegisterAndLoginComponent {


  formUser: User;
  model: User;
  constructor(private userService: UserService) {
    this.model = new User();
    this.formUser = new User();
  }
  onSubmit(registerform: any) {

    this.formUser.name = registerform.value.name;
    this.formUser.email = registerform.value.email;
    this.formUser.password = registerform.value.password;
    this.formUser.username = registerform.value.username;
    this.formUser.userType = UserType.USER;
    console.log(registerform.value);
    this.userService.addUser(this.formUser).subscribe();
    this.refresh();
  }
  refresh() {
    window.location.reload();
  }
}
