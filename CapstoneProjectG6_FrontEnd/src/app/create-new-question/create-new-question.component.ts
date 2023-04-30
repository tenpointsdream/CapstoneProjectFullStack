import { QuestionService } from './../service/question.service';
import { UserType } from './../entity/UserSubmit';
import { UserService } from './../service/user.service';
import { User } from './../entity/user.entity';
import { Question } from './../entity/question.entity';
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
  questionForm !: Question;
  model !: Question;
  constructor(
    private questionService: QuestionService,
    private httpClient: HttpClient,
    private cookieService: CookieService) {
  }
  onSubmit(questionform: any) {
    this.questionForm.title = questionform.value.title;
    this.questionForm.topic = questionform.value.topic;
    this.questionForm.descriptionQuestion = questionform.value.descriptionQuestion;
    this.questionForm.imageSrc = questionform.value.imageSrc;
    this.questionForm.status = false;
    this.questionForm.answers = [];
    this.questionForm.qapproved_by = new User();
    this.httpClient.get<UserProfile>(`http://localhost:8080/user/getbyusername/${this.cookieService.get('username')}`,
      {
        headers: { Authorization: `Bearer ${this.cookieService.get('jwtToken')}` }
        //withCredentials: true
      })
      .subscribe((userProfile: UserProfile) => {
        this.questionForm.qcreated_by = userProfile;
      });
    // todo
    this.questionService.addQuestion(this.questionForm).subscribe();
    this.refresh();
  }
  refresh() {
    window.location.reload();
  }
}
