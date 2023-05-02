import { QuestionService } from '../service/question.service';
import { UserType } from '../entity/UserSubmit';
import { UserService } from '../service/user.service';
import { User } from '../entity/user.entity';
import { Question } from '../entity/question.entity';
import { Component } from '@angular/core';
import { Answer } from '../entity/answer.entity';
import { HttpClient } from '@angular/common/http';
import { UserProfile } from '../entity/userprofile.entity';
import { CookieService } from 'ngx-cookie-service';
import { DatePipe } from '@angular/common';


@Component({
  selector: 'app-create-new-question',
  templateUrl: './create-new-question.component.html',
  styleUrls: ['./create-new-question.component.css']
})
export class CreateNewQuestionComponent {
  questionForm = {} as Question;
  model = {} as Question;
  currentUser = {} as User;
  constructor(
    private questionService: QuestionService,
    private httpClient: HttpClient,
    private cookieService: CookieService,
    private userService: UserService) {
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
    }
  }
  onSubmit(questionform: any) {
    this.questionForm.id = 1;
    this.questionForm.title = questionform.value.title;
    this.questionForm.topic = questionform.value.topic;
    this.questionForm.descriptionQuestion = questionform.value.descriptionQuestion;
    this.questionForm.status = false;
    this.questionForm.answers = [];
    //this.questionForm.datetime = Date.now().toString();

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
    this.questionService.addQuestion(this.questionForm).subscribe((createdQuestion: Question) => {
      console.log("Created Question: ", createdQuestion);
      alert("Your answer has been added! Waiting for admin to approve...");
    });
    // todo

    //this.refresh();
  }
  refresh() {
    window.location.reload();
  }
}
