import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { QuestionService } from '../service/question.service';
import { AnswerService } from '../service/answer.service';
import { Question } from '../entity/question.entity';
import { Answer } from '../entity/answer.entity';

@Component({
  selector: 'app-admin-home-page',
  templateUrl: './admin-home-page.component.html',
  styleUrls: ['./admin-home-page.component.css']
})
export class AdminHomePageComponent implements OnInit {
  questions = [] as Question[];
  answers = [] as Answer[];
  answerVisible: boolean;
  name: string = '';
  constructor(
    private router: Router,
    private cookieService: CookieService,
    private questionService: QuestionService,
    private answerService: AnswerService) {
      this.answerVisible = false;
  }
  ngOnInit(): void {
    this.name = this.cookieService.get('name');
    this.questionService.getQuestionByStatus(true).subscribe((data: Question[])=>{
      this.questions = data;
      console.log(this.questions);
    });
  }
  showAnswers(questionId: number) {
    this.answerVisible = true;
    this.answerService.getAnswerByQuestionId(questionId).subscribe((data: Answer[]) => {
      this.answers = data;
      console.log(this.answers);
    })
  }
  removeAnswer(answerId: number){
    if (confirm('Are you sure you want to delete this question?'))
      this.answerService.deleteAnswer(answerId).subscribe(() => {
        console.log("Answer is deleted, ID: ", answerId);
        this.refresh();
      });
  }

  removeQuestion(id: number) {
    if (confirm('Are you sure you want to delete this question?'))
      this.questionService.deleteQuestion(id).subscribe(() => {
        console.log("Question is deleted, ID: ", id);
        this.refresh();
      });
  }
  collapseList() {
    this.answerVisible = false;
  }
  refresh(): void {
    window.location.reload();
  }
  signout(): void {
    this.cookieService.deleteAll();
  }
}
