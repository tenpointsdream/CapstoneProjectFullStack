import { QuestionService } from './../service/question.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Question } from '../entity/question.entity';

@Component({
  selector: 'app-created-question',
  templateUrl: './created-question.component.html',
  styleUrls: ['./created-question.component.css']
})
export class CreatedQuestionComponent implements OnInit {
  questions: Question[] = [];
  constructor(private questionService : QuestionService){}
  ngOnInit(): void {
    this.questionService.getQuestionByStatus(true).subscribe((data: Question[]) => {
      this.questions = data;
    })
  }
}
