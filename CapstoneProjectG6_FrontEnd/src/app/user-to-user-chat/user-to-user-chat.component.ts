import { ActivatedRoute } from '@angular/router';
import { ChatService } from './../service/chat.service';
import { Component, OnInit } from '@angular/core';
import { Chat } from '../entity/chat';

@Component({
  selector: 'app-user-to-user-chat',
  templateUrl: './user-to-user-chat.component.html',
  styleUrls: ['./user-to-user-chat.component.css']
})
export class UserToUserChatComponent  {
  chat : Chat[] = [];
  model = {} as Chat;
  onVisible: boolean;
  constructor(private chatService: ChatService, private route: ActivatedRoute){
    this.onVisible = false;
  }
  // ngOnInit(): void {
  // this.chatService.getmsg(true).subscribe((date : Chat[])=> {
  //   this.chat = date;
  // })
  // }
  closeForm() {
    this.onVisible = false;
  }
  showForm() {
    this.onVisible = true;
  }
  onFileSelected(event: any) {

  }
  onSubmit(chatform: any) {

  }
}
