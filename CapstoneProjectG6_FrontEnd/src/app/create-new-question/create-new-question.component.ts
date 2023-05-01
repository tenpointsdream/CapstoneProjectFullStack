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
    this.questionForm.datetime = "05/01/2023, 4:42";
    console.log(this.cookieService.get('username'));
    console.log(this.questionForm);
    this.questionForm.qapproved_by = {} as User;
    this.userService.getUser(this.cookieService.get('username'))
      .subscribe((user: UserProfile) => {
        console.log(user);
        this.questionForm.qcreated_by = user;
        console.log("Question form: ", this.questionForm);
        console.log("Current user: " + this.questionForm.qcreated_by);
        this.questionService.addQuestion(this.questionForm).subscribe((createdQuestion: Question)=>{
          console.log("Created Question: ", createdQuestion);
        });
      });
    // todo
    
    //this.refresh();
  }
  refresh() {
    window.location.reload();
  }
}
