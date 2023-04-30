import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-admin-home-page',
  templateUrl: './admin-home-page.component.html',
  styleUrls: ['./admin-home-page.component.css']
})
export class AdminHomePageComponent {
  name: string;
  constructor(
    private router: Router,
    private cookieService: CookieService) {
    this.name = cookieService.get('name');
  }
  refresh(): void {
    window.location.reload();
  }
  signout(): void {
    this.cookieService.deleteAll();
  }
}
