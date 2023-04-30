import {Component, OnInit} from '@angular/core';
import {Question} from "./entity/question.entity";
import {QuestionService} from "./service/question.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  questions:Question[];

  constructor(private questionService:QuestionService) {
    this.questions = []
  }
  ngOnInit(): void {
    this.questionService.getQuestions()
  }
}
