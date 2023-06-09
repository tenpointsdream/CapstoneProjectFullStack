import { QuestionService } from '../service/question.service';
import { UserService } from '../service/user.service';
import { User } from '../entity/user.entity';
import { Question } from '../entity/question.entity';
import { Component, ViewChild, ElementRef } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Email } from '../entity/email.entity';
import { EmailService } from '../service/email.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';


@Component({
  selector: 'app-create-new-question',
  templateUrl: './create-new-question.component.html',
  styleUrls: ['./create-new-question.component.css']
})
export class CreateNewQuestionComponent {
  questionForm = {} as Question;
  model = {} as Question;
  admin = [] as User[];
  email = {} as Email;
  formGroup!: FormGroup;
  @ViewChild('fileInput') fileInput!: ElementRef;
  constructor(
    private questionService: QuestionService,
    private emailService: EmailService,
    private cookieService: CookieService,
    private userService: UserService,
    private formBuilder: FormBuilder,
    private httpClient: HttpClient,
    private router: Router) {
    this.formGroup = this.formBuilder.group({
      file: ['']
    });
    const now: Date = new Date();
    const options: Intl.DateTimeFormatOptions = {
      year: 'numeric',
      month: 'numeric',
      day: 'numeric',
      hour: 'numeric',
      minute: 'numeric',
      second: 'numeric'
    };
    const dateTimeString: string = now.toLocaleDateString('en-US', options);
    console.log(dateTimeString);
  }
  onFileSelected(event: any) {
    if (event.target.files.length > 0) {
      console.log(event.target.files[0].name);
      this.questionForm.imageSrc = event.target.files[0].name;
      this.questionForm.imageFile = event.target.files[0];
    }
  }
  onSubmit(questionform: any) {
    this.questionForm.id = 1;
    this.questionForm.title = questionform.value.title;
    this.questionForm.topic = questionform.value.topic;
    this.questionForm.descriptionQuestion = questionform.value.descriptionQuestion;
    this.questionForm.status = false;
    this.questionForm.answers = [];

    const now: Date = new Date();
    const options: Intl.DateTimeFormatOptions = {
      year: 'numeric',
      month: 'numeric',
      day: 'numeric',
      hour: 'numeric',
      minute: 'numeric',
      second: 'numeric'
    };
    const dateTimeString: string = now.toLocaleString('en-US', options);
    this.questionForm.datetime = dateTimeString;
    console.log("Current datetime: " + dateTimeString);
    console.log(this.cookieService.get('username'));
    console.log(this.questionForm);
    this.questionForm.qapproved_by = '';
    this.questionForm.qcreated_by = this.cookieService.get('username');
    console.log("Question form: ", this.questionForm);
    console.log("Current user: " + this.questionForm.qcreated_by);
    console.log("List of admin:", this.admin);
    this.userService.getAdmin().subscribe((users: User[]) => {
      this.admin = users;
      console.log("Admin?", users);
    });
    this.email.recipient = this.admin[0].email;
    this.email.msgBody = "You have a pending question to approve";
    this.email.subject = this.questionForm.title;
    console.log("Email to send: ", this.email);
    this.httpClient.post("http://localhost:8080/customer/sendemail", this.email, { headers: { Authorization: `Bearer ${this.cookieService.get('jwtToken')}` } })
      .subscribe((response) => {
        console.log("Token: ", this.cookieService.get('jwtToken'));
        console.log("Email to send:", this.email);
        console.log("Response: ", response);
      })
    this.questionService.addQuestion(this.questionForm, this.cookieService.get('username')).subscribe((response: string) => {
      console.log("Response: ", response);
      
    });
    alert("Your question has been added! Waiting for admin to approve...");

    this.fileInput.nativeElement.value = '';
  }
  sign_out() {
    this.cookieService.delete("username");
    this.cookieService.delete('jwtToken');
    this.router.navigate(["/home/userlogin"]);
  }
}
