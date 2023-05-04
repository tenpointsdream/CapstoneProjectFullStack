// @ts-ignore
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

// @ts-ignore
@Component({
  selector: 'app-page-not-found',
  templateUrl: './page-not-found.component.html',
  styleUrls: ['./page-not-found.component.css']
})
export class PageNotFoundComponent {

  constructor(private cookieService: CookieService,
    private router: Router) { }
  sign_out() {
    this.cookieService.delete("username");
    this.cookieService.delete('jwtToken');
    this.router.navigate(["/home/adminlogin"]);
  }
}
