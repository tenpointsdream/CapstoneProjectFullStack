import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Chat } from '../entity/chat.entity';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private baseUrl: string = "http://localhost:8080/customer/chat";
  constructor(private http: HttpClient, private cookieService: CookieService) { }
  getmsg(): Observable<Chat []>{
    const url = `${this.baseUrl}/getallmsg`
    return this.http.get<Chat []>(url,{headers: {Authorization: `Bearer ${this.cookieService.get('jwtToken')}`}});
  }
  addmsg(chat: Chat): Observable<Chat> {
    const url = `${this.baseUrl}/addmsg`;
    return this.http.post<Chat>(url, chat, { headers: { Authorization: `Bearer ${this.cookieService.get('jwtToken')}` } });
  }
  deletemsg(id: number){
    const url = `${this.baseUrl}/deletechatbyid/${id}`;
    return this.http.delete(url, { headers: {Authorization: `Bearer ${this.cookieService.get('jwtToken')}`}});
  }

}
