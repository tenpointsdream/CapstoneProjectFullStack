import { Component, ViewChild, ElementRef } from '@angular/core';
import { User } from '../entity/user.entity';
import { UserType } from '../entity/UserSubmit';
import { UserService } from '../service/user.service';
import { HttpClient } from '@angular/common/http';
import { UserProfile } from '../entity/userprofile.entity';

@Component({
  selector: 'app-user-sign-up',
  templateUrl: './user-sign-up.component.html',
  styleUrls: ['./user-sign-up.component.css']
})
export class UserSignUpComponent {
  formUser: User;
  model: User;
  @ViewChild('passwordInput') passwordInput!: ElementRef;
  constructor(private userService: UserService,
    private httpClient: HttpClient) {
    this.model = new User();
    this.formUser = new User();
  }
  onSubmit(registerform: any) {
    if (registerform.value.password.length < 7) {
      alert("Password must be at least 8 characters");
      this.passwordInput.nativeElement.select();
    } else {
      this.formUser.name = registerform.value.name;
      this.formUser.email = registerform.value.email;
      this.formUser.password = registerform.value.password;
      this.formUser.username = registerform.value.username;
      this.formUser.userType = UserType.USER;
      console.log(registerform.value);
      this.userService.addUser(this.formUser).subscribe();
      this.httpClient.get<User>(`http://localhost:8080/user/getbyusername/${this.formUser.username}`)
        .subscribe((user: User) => {
          if (user !== null) {
            alert("Username is taken!");
            window.location.reload();
          }
          else {
            this.goto();
          }
        });

    }
  }
  goto() {
    window.location.href = '/home/userlogin';
  }
}
