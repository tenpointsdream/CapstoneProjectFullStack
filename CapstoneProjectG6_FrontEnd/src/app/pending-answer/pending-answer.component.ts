import {Component, OnInit} from '@angular/core';
import {Answer} from "../entity/answer.entity";
import {QuestionService} from "../service/question.service";
import {AnswerService} from "../service/answer.service";
import {Question} from "../entity/question.entity";

@Component({
  selector: 'app-pending-answer',
  templateUrl: './pending-answer.component.html',
  styleUrls: ['./pending-answer.component.css']
})
export class PendingAnswerComponent implements OnInit {
  answers:Answer[];

  constructor(private answerService:AnswerService) {
    this.answers = []
  }
  ngOnInit(): void {
    this.answerService.getAnswers().subscribe((data:Answer[])=>{
      console.log(data);
      this.answers=data;
    })
  }


}
