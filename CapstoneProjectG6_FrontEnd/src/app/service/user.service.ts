import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../entity/user.entity';
@Injectable({
  providedIn: 'root'
})
export class UserService {
  login(userForm: User) {
    throw new Error('Method not implemented.');
  }

  private baseUrl = 'http://localhost:8080/user/adduser';
  constructor(private httpClient: HttpClient) { }
  addUser(user: User): Observable<User> {
    return this.httpClient.post<User>(this.baseUrl, user);
  }
}
