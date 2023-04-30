import { QuestionService } from './../service/question.service';
import { UserType } from './../entity/UserSubmit';
import { UserService } from './../service/user.service';
import { User } from './../entity/user.entity';
import { Question } from './../entity/question.entity';
import { Component } from '@angular/core';
import { Answer } from '../entity/answer.entity';
interface question {
  id: number;
  descriptionQuestion: string;
  imageSrc: string;
  status: string;
  topic: string;
  title: string;
  answers: Answer[];
  qcreated_by: User;
  qapproved_by: User;
}
@Component({
  selector: 'app-create-new-question',
  templateUrl: './create-new-question.component.html',
  styleUrls: ['./create-new-question.component.css']
})
export class CreateNewQuestionComponent {
  questionForm !: question;
  model !: question;
  constructor(private questionService: QuestionService) {
  }
  onSubmit(questionform: any) {
    this.questionForm.title = questionform.value.title;
    this.questionForm.topic = questionform.value.topic;
    this.questionForm.descriptionQuestion = questionform.value.descriptionQuestion;
    this.questionForm.imageSrc = questionform.value.imageSrc;
    // todo
    this.questionService.addQuestion(this.questionForm);
    this.refresh();
  }
  refresh() {
    window.location.reload();
  }
}
