import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Question } from '../entity/question.entity';
import { QuestionService } from '../service/question.service';
import { Answer } from '../entity/answer.entity';

@Component({
  selector: 'app-question-details-page',
  templateUrl: './question-details-page.component.html',
  styleUrls: ['./question-details-page.component.css']
})
export class QuestionDetailsPageComponent implements OnInit {

  questionID!: number;
  model = {} as Answer;
  question = {} as Question;
  onVisible:boolean;
  constructor(private route: ActivatedRoute,
    private questionService: QuestionService) {
      this.onVisible = false;
  }
  ngOnInit(): void {
    const idString = localStorage.getItem('questionId') ?? '0';
    this.questionID = parseInt(idString);
    console.log(this.questionID);
    this.questionService.getQuestionById(this.questionID).subscribe((data: any) => {
      console.log("ID: ", this.questionID);
      this.question = data;
      console.log("Question: ", this.question);
    })
  }
  closeForm(){
    this.onVisible = false;
  }
  showForm(){
    this.onVisible = true;
  }
  onFileSelected(event:any){

  }
  onSubmit(answerform: any) {

  }



}
