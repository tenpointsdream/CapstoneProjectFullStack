import { Chat } from '../entity/chat.entity';
import { ChatService } from '../service/chat.service';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { UserService } from '../service/user.service';
import { User } from '../entity/user.entity';

@Component({
  selector: 'app-user-to-user-chat',
  templateUrl: './user-to-user-chat.component.html',
  styleUrls: ['./user-to-user-chat.component.css']
})
export class UserToUserChatComponent implements OnInit {
  conversation = [] as Chat[];
  usersYouSent = new Set();
  messageReceivedFrom = new Set();
  chatYouSent = [] as Chat[];
  chat: Chat[] = [];
  chatToYou = [] as Chat[];
  model = {} as Chat;
  newMessage = {} as Chat;
  onVisible: boolean;
  chatToUser = [] as string[];
  chatToSpecificUser = [] as Chat[];
  chatFromSpecificUser = [] as Chat[];
  newChat = {} as Chat;
  newChatVisible: boolean;
  users = [] as User[];
  constructor(private chatService: ChatService,
    private cookieService: CookieService,
    private userService: UserService) {
    this.onVisible = false;
    this.newChatVisible = false;
  }
  ngOnInit(): void {
    this.getMessages();
  }

  getMessages() {
    this.chatService.getmsg().subscribe((data: Chat[]) => {
      this.chat = data;
      console.log("All chats: ", this.chat);
      this.chat.forEach(element => {
        // console.log("To user: ", element.to_user);
        console.log("Current user: ", this.cookieService.get('username'));
        if (element.to_user === this.cookieService.get('username')) {
          this.chatToYou.push(element);
          this.messageReceivedFrom.add(element.from_user);
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
    console.log("Chat you sent with you: ", this.chatYouSent);
    console.log("USERS ")
    console.log("you Sent to ", this.usersYouSent);
    console.log("You received messages from: ", this.messageReceivedFrom);
  }

  addMessage(chatform: NgForm) {
    this.model.from_user = this.cookieService.get('username');
    this.model.to_user = this.chatToUser[0];
    const now: Date = new Date();
    const options: Intl.DateTimeFormatOptions = {
      year: 'numeric',
      month: 'numeric',
      day: 'numeric',
      hour: 'numeric',
      minute: 'numeric',
      second: 'numeric'
    };
    this.model.datetime = now.toLocaleString('en-US', options);
    console.log("NEW MESSSSSSSSAGE: ", this.model);
    this.chatService.addmsg(this.model).subscribe((data: Chat) => {
      console.log("Message added: ", data);
      this.getMessages();
      chatform.resetForm();
    });
    window.location.reload();
  }

  closeForm() {
    this.onVisible = false;
  }

  showForm(from_user: string) {
    this.conversation = [];
    this.chatFromSpecificUser = [];
    this.chatToSpecificUser = [];
    this.chatToUser = [];
    this.onVisible = true;
    this.chatToYou.forEach(element => {
      if (element.from_user === from_user) {
        this.chatFromSpecificUser.push(element);
        this.conversation.push(element);
      }
    });
    this.chatYouSent.forEach(element => {
      if (element.to_user === from_user) {
        this.chatToSpecificUser.push(element);
        this.conversation.push(element);
      }
    })
    console.log("Chat with: ", from_user);
    console.log("They sent you: ", this.chatFromSpecificUser);
    console.log("You sent them: ", this.chatToSpecificUser);
    if (this.chatFromSpecificUser != null) {
      this.chatToUser.push(this.chatFromSpecificUser[0].from_user);
    }
    console.log("New message to:", this.chatToUser);
    this.conversation.sort((a, b) => a.id - b.id);
    console.log("Entire converation with this user: ", this.conversation);
    console.log("You are: ", this.cookieService.get('username'));
  }

  onFileSelected(event: any) {

  }

  onSubmit(chatform: NgForm) {
    this.addMessage(chatform);
  }
}
