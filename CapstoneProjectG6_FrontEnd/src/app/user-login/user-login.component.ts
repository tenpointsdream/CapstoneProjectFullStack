import { User } from './../entity/user.entity';
import { UserService } from './../service/user.service';
import { Component } from '@angular/core';
import { UserType } from '../entity/UserSubmit';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export default class UserLoginComponent {
userForm: User;
model : User;
constructor(private UserService: UserService) {
  this.userForm = new User();
  this.model = new User();
}
onSubmit(userForm : any){
  this.userForm.username = userForm.value.username;
  this.userForm.password = userForm.value.password;
  this.userForm.userType = UserType.USER;
  // this.UserService.login(this.userForm).subscribe();
  this.login();
}
// todo
login() {

}
}
