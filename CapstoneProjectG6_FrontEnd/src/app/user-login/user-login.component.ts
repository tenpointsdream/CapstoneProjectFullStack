import { User } from './../entity/user.entity';
import { Component, OnInit } from '@angular/core';
import { UserType } from '../entity/UserSubmit';
import { AuthenticationService } from '../service/authentication.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

interface UserProfile {
  id: number;
  name: string;
  username: string;
  password: string;
  email: string;
  userType: UserType;
}
@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent {
  private baseUrl: string = "http://localhost:8080/user/login";
  userForm: User;
  model: User;
  constructor(
    private authenticationService: AuthenticationService,
    private httpClient: HttpClient,
    private router: Router,
    private cookieService: CookieService) {
    this.userForm = new User();
    this.model = new User();
  }
  onSubmit(loginform: any) {
    this.userForm.username = loginform.value.username;
    this.userForm.password = loginform.value.password;
    //alert(`Username: ${this.userForm.username} Password:${this.userForm.password}`);
    this.httpClient.post('http://localhost:8080/user/authenticate',
      { username: this.userForm.username, password: this.userForm.password },
      { responseType: 'text' })
      .subscribe((response: any) => {
        const token = response;
        this.cookieService.set('jwtToken', token, 1, '/');
        console.log("Token: " + token);
        this.httpClient.get<UserProfile>(`http://localhost:8080/user/getbyusername/${this.userForm.username}`,
          {
            headers: { Authorization: `Bearer ${token}` }
            //withCredentials: true
          })
          .subscribe((userProfile: UserProfile) => {
            console.log('Logged in as: ', userProfile.username);
            console.log('Email: ', userProfile.email);
            console.log('User type: ' + userProfile.userType);
          });
        this.router.navigate(['userhomepage']);
      }, (err) => {
        alert("Invalid username/password");
      });
  }
}
