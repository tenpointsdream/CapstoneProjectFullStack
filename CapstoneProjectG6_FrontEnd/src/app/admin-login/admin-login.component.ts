import { UserService } from './../service/user.service';
import { Component } from '@angular/core';
import { User } from '../entity/user.entity';
import { UserType } from '../entity/UserSubmit';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent {
userForm: User;
model: User;
constructor(private UserService: UserService){
  this.userForm = new User();
  this.model = new User();
}
onSubmit(adminForm : any){
  this.userForm.email = adminForm.value.email;
  this.userForm.password = adminForm.value.password;
  this.userForm.userType = UserType.ADMIN;
  // this.UserService.login(this.userForm).subscribe();
  this.login();
}
// todo
login() {

}
}
