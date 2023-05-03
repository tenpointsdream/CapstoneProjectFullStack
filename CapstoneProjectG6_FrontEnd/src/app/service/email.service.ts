import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Email } from '../entity/email.entity';

@Injectable({
  providedIn: 'root'
})
export class EmailService {

  private baseUrl: string = 'http://localhost:8080/customer/sendemail';
  constructor(
    private httpClient: HttpClient,
    private cookieService: CookieService) { }

  sendEmail(email: Email): any {
    this.httpClient.post<any>(this.baseUrl, email, { headers: { Authorization: `Bearer ${this.cookieService.get('jwtToken')}` } });
  }
}
