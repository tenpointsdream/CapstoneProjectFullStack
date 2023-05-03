import { Component, OnInit } from '@angular/core';
import { Question } from "../entity/question.entity";
import { QuestionService } from "../service/question.service";
import { HttpClient } from "@angular/common/http";
import { CookieService } from 'ngx-cookie-service';
import { UserProfile } from '../entity/userprofile.entity';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-pending-question',
  templateUrl: './pending-question.component.html',
  styleUrls: ['./pending-question.component.css']
})
export class PendingQuestionComponent implements OnInit {
  pendingQuestions: Question[];
  questionToUpdate = {} as Question;
  constructor(
    private questionService: QuestionService,
    private http: HttpClient,
    private cookieService: CookieService,
    private userService: UserService) {
    this.pendingQuestions = [];
  }
  ngOnInit(): void {
    console.log(this.cookieService.get('username'));
    this.questionService.getPendingQuestion().subscribe((data: Question[]) => {
      console.log(data);
      this.pendingQuestions = data;
    })
  }

  approveQuestion(id: number) {
    if (confirm('Are you sure you want to approve this question?'))
      this.questionService.getQuestionById(id).subscribe((question: Question) => {
        console.log(question);
        this.questionToUpdate = question;
        this.questionToUpdate.status = true;
        console.log(this.questionToUpdate);
        this.userService.getUser(this.cookieService.get('username')).subscribe((user: UserProfile) => {
          console.log(user);
          this.questionToUpdate.qapproved_by = this.cookieService.get('username');
          console.log("Question before to approve: ", this.questionToUpdate);
          this.questionService.updateQuestion(id, this.questionToUpdate).subscribe((updatedQuestion: Question) => {
            console.log(updatedQuestion);
            this.refresh();
          });
        });
      })
  }
  removeQuestion(id: number) {
    if (confirm('Are you sure you want to delete this question?'))
      this.questionService.deleteQuestion(id).subscribe(() => {
        console.log("Question is deleted, ID: ", id);
        this.refresh();
      });
  }

  refresh(): void {
    window.location.reload();
  }
}

export { QuestionService };
