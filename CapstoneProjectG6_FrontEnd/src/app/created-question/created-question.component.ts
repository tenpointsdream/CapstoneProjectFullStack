import { Answer } from '../entity/answer.entity';
import { Question } from '../entity/question.entity';
import { AnswerService } from '../service/answer.service';
import { QuestionService } from '../service/question.service';
import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Email } from '../entity/email.entity';
import { EmailService } from '../service/email.service';
import { UserService } from '../service/user.service';
import { User } from '../entity/user.entity';
import { elementAt } from 'rxjs';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-created-question',
  templateUrl: './created-question.component.html',
  styleUrls: ['./created-question.component.css']
})
export class CreatedQuestionComponent implements OnInit {
  isANull: boolean;
  answerExist: boolean;
  questions: Question[] = [];
  answerVisible: boolean;
  model = {} as Answer;
  answers = [] as Answer[];
  admin = [] as User[];
  answerForm = {} as Answer;
  email = {} as Email;
  onVisible: boolean;
  q_id: number = 0;
  question = {} as Question;
  constructor(private questionService: QuestionService,
    private answerService: AnswerService,
    private cookieService: CookieService,
    private userService: UserService,
    private emailService: EmailService,
    private router: Router,
    private httpClient: HttpClient) {
    this.onVisible = false;
    this.answerVisible = false;
    this.answerExist = true;
    this.isANull = false;
  }
  ngOnInit(): void {
    this.questionService.getQuestionByStatus(true).subscribe((data: Question[]) => {
      this.questions = data;
      // const idString = localStorage.getItem('questionId') ?? '0';
      // this.questionID = parseInt(idString);
      // console.log(this.questionID);
      // this.questionService.getQuestionById(this.questionID).subscribe((data: any) => {
      //   console.log("ID: ", this.questionID);
      //   this.questions = data;
      //   console.log("Question: ", this.questions);
    })
  }
  hideAnswers(){
    this.answerVisible = false;
  }
  showAnswers(questionId: number) {
    this.answerVisible = true;
    this.answerService.getAnswerByQuestionId(questionId).subscribe((data: Answer[]) => {
      this.answers = data;
      console.log(this.answers);
      if (data.length === 0) {
        this.answerExist = false;
        this.isANull = true;
      }
    })
  }
  collapseList() {
    this.answerVisible = false;
  }
  closeForm() {
    this.onVisible = false;
  }
  showForm(question_id: number) {
    this.onVisible = true;
    this.q_id = question_id;
  }
  onFileSelected(event: any) {
    if (event.target.files.length > 0) {
      this.answerForm.img_src = event.target.files[0].name;
      this.answerForm.imageFile = event.target.files[0];
    }
  }
  onSubmit(answerform: any) {
    this.answerForm.id = 1;
    this.answerForm.description_answer = answerform.value.description_answer;
    this.answerForm.approved_by = '';
    this.answerForm.question = this.question;
    this.answerForm.status = false;
    const now: Date = new Date();
    const options: Intl.DateTimeFormatOptions = {
      year: 'numeric',
      month: 'numeric',
      day: 'numeric',
      hour: 'numeric',
      minute: 'numeric',
      second: 'numeric'
    };

    this.userService.getAdmin().subscribe((users: User[]) => {
      this.admin = users;
    });
    this.email.recipient = this.admin[0].email;
    this.email.msgBody = "You have a pending question to approve";
    this.email.subject = this.question.title;
    console.log("Email to send: ", this.email);
    this.httpClient.post("http://localhost:8080/customer/sendemail", this.email, { headers: { Authorization: `Bearer ${this.cookieService.get('jwtToken')}` } })
      .subscribe((response) => {
        console.log("Token: ", this.cookieService.get('jwtToken'));
        console.log("Email to send:", this.email);
        console.log("Response: ", response);
      });
    //alert("Email notification sent to admin!");

    this.answerForm.datetime = now.toLocaleString('en-US', options);
    this.answerForm.created_by = this.cookieService.get('username');
    this.answerService.addAnswer(this.answerForm, this.cookieService.get('username'), this.q_id).subscribe((response) => {
      console.log("Response: ", response);
    });
    alert("Your answer hase been added! Waiting for admin approval...");
    this.closeForm();
  }
  sign_out() {
    this.cookieService.delete("username");
    this.cookieService.delete('jwtToken');
    this.router.navigate(["/home/userlogin"]);
  }
}
