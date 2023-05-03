import { Chat } from './../entity/chat.entity';
import { ActivatedRoute } from '@angular/router';
import { ChatService } from './../service/chat.service';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-user-to-user-chat',
  templateUrl: './user-to-user-chat.component.html',
  styleUrls: ['./user-to-user-chat.component.css']
})
export class UserToUserChatComponent implements OnInit {
  usersYouSent = new Set();
  chatYouSent = [] as Chat[];
  chat: Chat[] = [];
  chatToYou = [] as Chat[];
  model = {} as Chat;
  onVisible: boolean;
  constructor(private chatService: ChatService,
    private cookieService: CookieService) {
    this.onVisible = false;
  }
  ngOnInit(): void {
    this.getMessages();
  }

  getMessages() {
    this.chatService.getmsg().subscribe((data: Chat[]) => {
      this.chat = data;
      console.log("All chats: ", this.chat);
      this.chat.forEach(element => {
        console.log("To user: ", element.to_user);
        console.log("Current user: ", this.cookieService.get('username'));
        if (element.to_user === this.cookieService.get('username')) {
          this.chatToYou.push(element);
        }
      });
      this.chat.forEach(element => {
        if (element.from_user === this.cookieService.get('username')) {
          console.log("From you: ", element.from_user);
          this.chatYouSent.push(element);
          this.usersYouSent.add(element.to_user);
        }
      });
    });
    console.log("Chat you received: ", this.chatToYou);   
    console.log("USERs having conversation with you: ", this.chatYouSent);
    console.log("you Sent to " , this.usersYouSent);
  }

  addMessage(chatform: NgForm) {
    this.model.from_user = "";
    this.model.to_user = "";
    this.chatService.addmsg(this.model).subscribe(() => {
      this.getMessages();
      chatform.resetForm();
    });
  }

  closeForm() {
    this.onVisible = false;
  }

  showForm() {
    this.onVisible = true;
  }

  onFileSelected(event: any) {

  }

  onSubmit(chatform: NgForm) {
    this.addMessage(chatform);
  }
}