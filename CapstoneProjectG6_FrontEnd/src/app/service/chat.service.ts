import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private baseUrl: string = "http://localhost:8080/";
  
  constructor() { }
  getmsg(arg0: boolean) {
    throw new Error('Method not implemented.');
  }
}
