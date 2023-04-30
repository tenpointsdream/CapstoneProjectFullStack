import {Component, OnInit} from '@angular/core';
import {Question} from "../entity/question.entity";
import {QuestionService} from "../service/question.service";

@Component({
  selector: 'app-pending-question',
  templateUrl: './pending-question.component.html',
  styleUrls: ['./pending-question.component.css']
})
export class PendingQuestionComponent implements OnInit {
  questions:Question[];

  constructor(private questionService:QuestionService) {
    this.questions = []
  }
  ngOnInit(): void {
    this.questionService.getQuestions().subscribe((data:Question[])=>{
      console.log(data);
      this.questions=data;
    })
  }
}

export { QuestionService } ;
