import { QuestionService } from './../service/question.service';
import { UserType } from './../entity/UserSubmit';
import { UserService } from './../service/user.service';
import { User } from './../entity/user.entity';
import { Question } from './../entity/question.entity';
import { Component } from '@angular/core';

@Component({
  selector: 'app-create-new-question',
  templateUrl: './create-new-question.component.html',
  styleUrls: ['./create-new-question.component.css']
})
export class CreateNewQuestionComponent {
  questionForm : Question;
  model : Question;
  constructor(private questionService: QuestionService){
    this.questionForm = new Question();
    this.model = new Question();
  }
  onSubmit(questionform: any){
    this.questionForm.title = questionform.value.title;
    this.questionForm.topic = questionform.value.topic;
    this.questionForm.descriptionQuestion = questionform.value.descriptionQuestion;
    this.questionForm.imageSrc = questionform.value.imageSrc;
    // todo
    this.questionService.addQuestion(this.questionForm);
    this.refresh();
  }
  refresh(){
    window.location.reload();
  }
}
