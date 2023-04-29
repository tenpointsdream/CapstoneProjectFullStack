import { User } from './../entity/user.entity';
import { UserService } from './../service/user.service';
import { Component, OnInit } from '@angular/core';
import { UserType } from '../entity/UserSubmit';
import { AuthenticationService } from '../service/authentication.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent {
  private baseUrl: string = "http://localhost:8080/user/login";
  userForm: User;
  model: User;
  isLoggedIn: boolean;
  constructor(
    private authenticationService: AuthenticationService,
    private httpClient: HttpClient,
    private router: Router) {
    this.userForm = new User();
    this.model = new User();
    this.isLoggedIn = false;
  }
  onSubmit(loginform: any) {
    this.userForm.username = loginform.value.username;
    this.userForm.password = loginform.value.password;
    this.userForm.userType = UserType.USER;
    // this.UserService.login(this.userForm).subscribe();
    const url = `${this.baseUrl}/${this.userForm.username}/${this.userForm.password}`;
    //alert(url);
    this.httpClient.get<boolean>(url).subscribe(
      (response) => {
        if (response) {
          this.authenticationService.setToken("authorized");
          console.log("Logging in");
          this.isLoggedIn = true;
          this.router.navigate(["createdquestion"]);
        }
      }, (err) => {
        this.authenticationService.logout();
        alert("Invalid username/password");
      });
  }
}
