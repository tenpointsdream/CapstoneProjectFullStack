import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { UserService } from '../service/user.service';
@Component({
  selector: 'app-user-home-page',
  templateUrl: './user-home-page.component.html',
  styleUrls: ['./user-home-page.component.css']
})
export class UserHomePageComponent implements OnInit {
  name: string = '';
  username: string = '';
  constructor(
    private router: Router,
    private cookieService: CookieService,
    private userService: UserService) {
    //this.name = cookieService.get('name');
    //this.refresh();
  }
  ngOnInit(): void {
    this.name = this.cookieService.get('name');
    this.username = this.cookieService.get('username');
    //alert(this.name + " " + this.username);
  }

  refresh(): void {
    window.location.reload();
  }
  sign_out(): void {
    //this.cookieService.deleteAll();
    this.router.navigate(["/home/userlogin"]);
    this.cookieService.delete("username");
    this.cookieService.delete('jwtToken');
  }
}
