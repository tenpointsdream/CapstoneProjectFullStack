// @ts-ignore
import { Component } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

// @ts-ignore
@Component({
  selector: 'app-page-not-found',
  templateUrl: './page-not-found.component.html',
  styleUrls: ['./page-not-found.component.css']
})
export class PageNotFoundComponent {

  constructor(private cookieService: CookieService) { }
  sign_out() {
    this.cookieService.deleteAll();
  }
}
