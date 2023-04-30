import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
@Component({
  selector: 'app-user-home-page',
  templateUrl: './user-home-page.component.html',
  styleUrls: ['./user-home-page.component.css']
})
export class UserHomePageComponent {
  name: string = this.cookieService.get('name');
  constructor(
    private router: Router,
    private cookieService: CookieService) {
    //this.name = cookieService.get('name');
    //this.refresh();
  }

  refresh(): void {
    window.location.reload();
  }
  signout(): void {
    this.cookieService.deleteAll();
  }
}
