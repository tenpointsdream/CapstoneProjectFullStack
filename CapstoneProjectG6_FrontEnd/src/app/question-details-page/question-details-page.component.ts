import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Question} from '../entity/question.entity';
import {QuestionService} from '../service/question.service';
import {Answer} from '../entity/answer.entity';
import {AnswerService} from '../service/answer.service';
import {EmailService} from '../service/email.service';
import {Email} from '../entity/email.entity';
import {UserService} from '../service/user.service';
import {User} from '../entity/user.entity';
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'app-question-details-page',
  templateUrl: './question-details-page.component.html',
  styleUrls: ['./question-details-page.component.css']
})
export class QuestionDetailsPageComponent implements OnInit {
  email = {} as Email;
  admin = [] as User[];
  questionID!: number;
  model = {} as Answer;
  question = {} as Question;
  answers = [] as Answer[];
  onVisible: boolean;
  answerForm = {} as Answer;
  constructor(private route: ActivatedRoute,
              private questionService: QuestionService,
              private answerService: AnswerService,
              private emailService: EmailService,
              private userService: UserService,
              private cookieService: CookieService) {
    this.onVisible = false;
  }
  ngOnInit(): void {
    const idString = localStorage.getItem('questionId') ?? '0';
    this.questionID = parseInt(idString);
    console.log(this.questionID);
    this.questionService.getQuestionById(this.questionID).subscribe((data: any) => {
      console.log("ID: ", this.questionID);
      this.question = data;
      console.log("Question: ", this.question);
    })
    this.answerService.getAnswerByQuestionId(this.questionID).subscribe((data: any)=>{
      console.log("Answers: ",data);
      this.answers = data;
    })
  }

  onFileSelected(event: any) {
    if (event.target.files.length > 0) {
      console.log(event.target.files[0].name);
      this.answerForm.img_src = event.target.files[0].name;
    }
  }
  closeForm() {
    this.onVisible = false;
  }
  showForm() {
    this.onVisible = true;
  }
  onSubmit(answerform: any) {
    this.answerForm.id = 1;
    this.answerForm.created_by = this.cookieService.get('username')
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
    this.answerForm.datetime = now.toLocaleString('en-US', options);
    this.answerService.addAnswer(this.answerForm, this.cookieService.get('username')).subscribe((returnAnswer: Answer) => {
      console.log(returnAnswer);
      alert("You answer has been added! Waiting for admin approval...");
      this.email.msgBody = 'You have new pending question to approve';
      this.email.subject = this.question.title;
      this.userService.getAdmin().subscribe((users: User[]) => {
        this.admin = users;
        console.log(users);
        this.admin.forEach(element => {
          this.email.recipient = element.email;
          this.emailService.sendEmail(this.email).subscribe((message: any) => {
            console.log(message);
            console.log("Sent to: ", element.name);
          });
        });
      });
    });

  }
  logout() {
    localStorage.clear();
  }
}
