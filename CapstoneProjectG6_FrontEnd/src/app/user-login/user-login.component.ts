import { User } from './../entity/user.entity';
import { Component, OnInit } from '@angular/core';
import { UserType } from '../entity/UserSubmit';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { UserProfile } from '../entity/userprofile.entity';
import { UserService } from '../service/user.service';
@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent {
  private baseUrl: string = "http://localhost:8080/user/login";
  userForm: User;
  model: User;
  userChecked: User = new User();

  role: string = '';
  constructor(
    private userService: UserService,
    private httpClient: HttpClient,
    private router: Router,
    private cookieService: CookieService) {
    this.userForm = new User();
    this.model = new User();
  }
  onSubmit(loginform: any) {
    this.userForm.username = loginform.value.username;
    this.userForm.password = loginform.value.password;
    console.log("User name is: " + this.userForm.username);
    console.log("User type before: " + this.role);
    this.httpClient.get<UserProfile>(`http://localhost:8080/user/getbyusername/${this.userForm.username}`)
      .subscribe((userdetails: UserProfile) => {
        if (userdetails.userType.toString() === 'ADMIN') {
          alert("FOR USERS ONLY!");
        }
        else {
          //alert(`Username: ${this.userForm.username} Password:${this.userForm.password}`);
          this.httpClient.post('http://localhost:8080/user/authenticate',
            { username: this.userForm.username, password: this.userForm.password },
            { responseType: 'text' })
            .subscribe((response: any) => {
              const token = response;
              this.cookieService.set('jwtToken', token, 1, '/');
              // console.log("Token: " + token);
              this.httpClient.get<UserProfile>(`http://localhost:8080/user/getbyusername/${this.userForm.username}`,
                {
                  headers: { Authorization: `Bearer ${token}` }
                })
                .subscribe((userProfile: UserProfile) => {
                  console.log('Logged in as: ', userProfile.name);
                  this.cookieService.set('name', userProfile.name);
                  console.log('Email: ', userProfile.email);
                  this.cookieService.set('username', userProfile.username);
                  console.log('Username: ', this.cookieService.get('username'));
                });
              this.router.navigate(['userhomepage']);
            }, (err) => {
              alert("Invalid username/password");
            });
        }
      });

  }
}

