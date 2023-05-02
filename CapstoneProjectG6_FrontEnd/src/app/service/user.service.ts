import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../entity/user.entity';
import { UserProfile } from '../entity/userprofile.entity';
import { UserType } from '../entity/UserSubmit';
import { CookieService } from 'ngx-cookie-service';
@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8080/user';
  constructor(private httpClient: HttpClient,
    private cookieService: CookieService) { }
  addUser(user: User): Observable<User> {
    const url = `${this.baseUrl}/adduser`;
    return this.httpClient.post<User>(url, user);
  }

  getUser(username: string): Observable<UserProfile> {
    const url = `${this.baseUrl}/getbyusername/${username}`;
    return this.httpClient.get<UserProfile>(url);
  }

  getUserByType(userType: UserType): Observable<UserProfile[]> {
    const url = `${this.baseUrl}/getbytype/${userType}}`;
    return this.httpClient.get<UserProfile[]>(url, { headers: { Authorization: `Bearer ${this.cookieService.get('jwtToken')}` } });
  }
  getAdmin(): Observable<UserProfile[]> {
    const url = `${this.baseUrl}/getbytype/ADMIN`;
    return this.httpClient.get<UserProfile[]>(url, { headers: { Authorization: `Bearer ${this.cookieService.get('jwtToken')}` } });
  }
}
