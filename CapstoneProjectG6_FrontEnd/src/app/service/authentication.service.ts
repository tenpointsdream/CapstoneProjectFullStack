import { Injectable } from '@angular/core';

const TOKEN = 'u_token';
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor() { }

  setToken(importtoken: string) {
    window.localStorage.removeItem(TOKEN)
    window.localStorage.setItem(TOKEN, importtoken);
  }
  getToken() {
    return window.localStorage.getItem(TOKEN);
  }
  logout() {
    window.localStorage.removeItem(TOKEN);
  }
}
