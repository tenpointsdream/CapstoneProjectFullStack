import { UserService } from './../service/user.service';
import { Component } from '@angular/core';
import { User } from '../entity/user.entity';
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
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent {
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
  onSubmit(adminForm: any) {
    this.userForm.email = adminForm.value.email;
    this.userForm.password = adminForm.value.password;
    // this.UserService.login(this.userForm).subscribe();
    this.httpClient.post('http://localhost:8080/user/authenticate',
      { username: this.userForm.username, password: this.userForm.password },
      { responseType: 'text' })
      .subscribe((response: any) => {
        const token = response;
        this.cookieService.set('jwtToken', token, 1, '/');
        console.log("Token: " + token);
        this.httpClient.get<UserProfile>(`http://localhost:8080/user/getbyusername/${this.userForm.username}`,
          {
            headers: { Authorization: `Bearer ${token}` }//,
            //withCredentials: true
          })
          .subscribe((userProfile: UserProfile) => {
            console.log('Logged in as: ', userProfile.username);
            console.log('Email: ', userProfile.email);
            console.log('User type: ' + userProfile.userType);
          });
        this.router.navigate(['createdquestion']);
      }, (err) => {
        alert("Invalid username/password");
      });
  }
}