import { Answer } from './../entity/answer.entity';
import { Question } from '../entity/question.entity';
import { QuestionService } from '../service/question.service';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Email } from '../entity/email.entity';
import { User } from '../entity/user.entity';
import { ActivatedRoute } from '@angular/router';
import { AnswerService } from '../service/answer.service';
import { EmailService } from '../service/email.service';
import { UserService } from '../service/user.service';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-created-question',
  templateUrl: './created-question.component.html',
  styleUrls: ['./created-question.component.css']
})
export class CreatedQuestionComponent implements OnInit {
  q_id: number = 0;
  email = {} as Email;
  admin = [] as User[];
  questionID!: number;
  model = {} as Answer;
  questions: Question[] = [];
  answers = [] as Answer[];
  onVisible: boolean;
  answerForm = {} as Answer;
  formGroup!: FormGroup;
  @ViewChild('fileInput') fileInput!: ElementRef;
  question!: Question;
  constructor(private route: ActivatedRoute,
    private questionService: QuestionService,
    private answerService: AnswerService,
    private emailService: EmailService,
    private userService: UserService,
    private cookieService: CookieService,
    private formBuilder: FormBuilder) {
    this.onVisible = false;
    this.formGroup = this.formBuilder.group({
      file: ['']
    });
  }
  ngOnInit(): void {
    const idString = localStorage.getItem('questionId') ?? '0';
    this.questionID = parseInt(idString);
    console.log(this.questionID);
    this.questionService.getQuestionById(this.questionID).subscribe((data: any) => {
      console.log("ID: ", this.questionID);
      this.questions = data;
      console.log("Question: ", this.questions);
    })
    this.answerService.getAnswerByQuestionId(this.questionID).subscribe((data: any) => {
      console.log("Answers: ", data);
      this.answers = data;
    })
  }
  onFileSelected(event: any) {
    if (event.target.files.length > 0) {
      console.log(event.target.files[0].name);
      this.answerForm.img_src = event.target.files[0].name;
      this.answerForm.imageFile = event.target.files[0];
    }
  }
  closeForm() {
    this.onVisible = false;
    this.fileInput.nativeElement.value = '';
    this.model.description_answer = '';
  }
  showForm(q_id: number) {
    this.onVisible = true;
    this.q_id = q_id;
  }
  onSubmit(answerform: any) {
    this.answerForm.id = 1;
    this.answerForm.description_answer = answerform.value.description_answer;
    this.answerForm.approved_by = '';
    this.answerForm.question = this.question;

    console.log("Question on this: ", this.answerForm.question);
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

    this.answerForm.created_by = this.cookieService.get('username');
    console.log("Answer before submitting: ", this.answerForm);
    this.answerService.addAnswer(this.answerForm, this.cookieService.get('username'), this.q_id).subscribe((response) => {
      console.log("Response: ", response);
    });
    alert("You answer has been added! Waiting for admin approval...");
    this.email.msgBody = 'You have new pending question to approve';
    // this.email.subject = this.questions.title;
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
    alert("Your answer has been created. Waiting for admin approval.");
    this.closeForm();
  }

}
