import { Component } from '@angular/core';
import { User } from '../entity/user.entity';
import { UserType } from '../entity/UserSubmit';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-user-sign-up',
  templateUrl: './user-sign-up.component.html',
  styleUrls: ['./user-sign-up.component.css']
})
export class UserSignUpComponent {
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
