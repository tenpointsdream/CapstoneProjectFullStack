import { Answer } from './../entity/answer.entity';
import { AnswerService } from './../service/answer.service';
import { Question } from './../entity/question.entity';
import { QuestionService } from './../service/question.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-created-question',
  templateUrl: './created-question.component.html',
  styleUrls: ['./created-question.component.css']
})
export class CreatedQuestionComponent implements OnInit {
  questions: Question[] = [];
  // showForm = false;
  model = {} as Answer;
  questionForm: any;
  // answerForm : any;
  questionID!: number;
  // model = {} as Answer;
  // question = {} as Question;
  onVisible: boolean;
  constructor(private questionService : QuestionService, private answerService : AnswerService,
    ){
      this.onVisible = false;
    }
  ngOnInit(): void {
    this.questionService.getQuestionByStatus(true).subscribe((data: Question[]) => {
      this.questions = data;
    // const idString = localStorage.getItem('questionId') ?? '0';
    // this.questionID = parseInt(idString);
    // console.log(this.questionID);
    // this.questionService.getQuestionById(this.questionID).subscribe((data: any) => {
    //   console.log("ID: ", this.questionID);
    //   this.questions = data;
    //   console.log("Question: ", this.questions);
    })
  }
  // onFileSelected(event: any) {
  //   if (event.target.files.length > 0) {
  //     console.log(event.target.files[0].name);
  //     this.questionForm.imageSrc = event.target.files[0].name;
  //   }
  // }
  // listOfAnswer(id: number) {
  //   this.answerService.getAnswerByQuestionId(id).subscribe((answer: Answer[])=> {

  //   })
  // }
  // onSubmit(answerForm: any) {
  //   this.answerForm.id = 1;
  //   this.answerForm.description_answer = answerForm.value.description_answer;
  //   this.answerForm.img_src = answerForm.value.img_src;
  //   this.answerForm.status = false;
  //   this.answerForm.answers = [];
  //   const now: Date = new Date();
  //   const options: Intl.DateTimeFormatOptions = {
  //     year: 'numeric',
  //     month: 'numeric',
  //     day: 'numeric',
  //     hour: 'numeric',
  //     minute: 'numeric',
  //     second: 'numeric'
  //   };
  // }
  closeForm() {
    this.onVisible = false;
  }
  showForm() {
    this.onVisible = true;
  }
  onFileSelected(event: any) {

  }
  onSubmit(answerform: any) {

  }

}
