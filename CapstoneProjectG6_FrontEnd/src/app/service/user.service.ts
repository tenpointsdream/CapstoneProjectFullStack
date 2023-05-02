import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../entity/user.entity';
import { UserProfile } from '../entity/userprofile.entity';
@Injectable({
  providedIn: 'root'
})
export class UserService {

  private user: any;
  setUser(user: any) {
    this.user = user;
  }
  optainUser() {
    return this.user;
  }

  private baseUrl = 'http://localhost:8080/user';
  constructor(private httpClient: HttpClient) { }
  addUser(user: User): Observable<User> {
    const url = `${this.baseUrl}/adduser`;
    return this.httpClient.post<User>(url, user);
  }

  getUser(username: string): Observable<UserProfile> {
    const url = `${this.baseUrl}/getbyusername/${username}`;
    return this.httpClient.get<UserProfile>(url);
  }
}
