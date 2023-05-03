import { Chat } from './../entity/chat.entity';
import { ActivatedRoute } from '@angular/router';
import { ChatService } from './../service/chat.service';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-user-to-user-chat',
  templateUrl: './user-to-user-chat.component.html',
  styleUrls: ['./user-to-user-chat.component.css']
})
export class UserToUserChatComponent implements OnInit {
  chat : Chat[] = [];
  model = {} as Chat;
  onVisible: boolean;
  constructor(private chatService: ChatService){
    this.onVisible = false;
  }
  ngOnInit(): void {
    this.getMessages();
  }

  getMessages() {
    this.chatService.getmsg().subscribe((data: Chat[]) => {
      this.chat = data;
    })
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